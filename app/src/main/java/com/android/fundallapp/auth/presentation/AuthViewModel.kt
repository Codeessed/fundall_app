package com.android.fundallapp.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundallapp.auth.data.model.login.LoginRequest
import com.android.fundallapp.auth.data.model.login.LoginResponse
import com.android.fundallapp.auth.data.model.signup.SignUpRequest
import com.android.fundallapp.auth.data.model.signup.SignUpResponse
import com.android.fundallapp.auth.data.remotedata.network.AuthApiInterface
import com.android.fundallapp.auth.domain.repository.AuthRepository
import com.android.fundallapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    private val _addUser = Channel<AuthEvent>()
    val addUser = _addUser.receiveAsFlow()

    private val _loginUser = Channel<AuthEvent>()
    val loginUser = _loginUser.receiveAsFlow()

    sealed class AuthEvent{
        class SignUpSuccess(val result: SignUpResponse): AuthEvent()
        class LoginSuccess(val result: LoginResponse): AuthEvent()
        class Failure(val errorText: String): AuthEvent()
        object Loading: AuthEvent()
        object Empty: AuthEvent()
    }

    fun signUp(signUpRequest: SignUpRequest){
        viewModelScope.launch {
            _addUser.send(AuthEvent.Loading)
            try {
                when(val result = authRepository.signUp(signUpRequest)){
                    is Resource.Success -> _addUser.send(AuthEvent.SignUpSuccess(result.data!!))
                    is Resource.Failure -> _addUser.send(AuthEvent.Failure(result.message!!))
                }
            }catch (e: Exception){
                when(e){
                    is IOException -> _addUser.send(AuthEvent.Failure("Weak connection"))
                    else -> _addUser.send(AuthEvent.Failure(e.message.toString()))
                }
            }
        }
    }

    fun signIn(loginRequest: LoginRequest){
        viewModelScope.launch {
            _loginUser.send(AuthEvent.Loading)
            try {
                when(val result = authRepository.signIn(loginRequest)){
                    is Resource.Success ->{
                        _loginUser.send(AuthEvent.LoginSuccess(result.data!!))
                    }
                    is Resource.Failure -> {
                        _loginUser.send(AuthEvent.Failure(result.message!!))
                    }
                }
            }catch (e: Exception){
                when(e){
                    is IOException -> _loginUser.send(AuthEvent.Failure("Weak connection"))
                    else ->  {
                        _loginUser.send(AuthEvent.Failure("An error occurred $e"))
                    }
                }
            }
        }
    }

}