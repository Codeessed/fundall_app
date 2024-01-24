package com.android.fundallapp.auth.domain.repository

import com.android.fundallapp.auth.data.model.ErrorData
import com.android.fundallapp.auth.data.model.login.LoginRequest
import com.android.fundallapp.auth.data.model.login.LoginResponse
import com.android.fundallapp.auth.data.model.signup.SignUpRequest
import com.android.fundallapp.auth.data.model.signup.SignUpResponse
import com.android.fundallapp.auth.data.remotedata.network.AuthApiInterface
import com.android.fundallapp.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import javax.inject.Inject


class AuthRepository @Inject constructor(private val authApiInterface: AuthApiInterface)
    : AuthRepositoryImpl{
    override suspend fun signUp(signUpRequest: SignUpRequest): Resource<SignUpResponse> {
        val response = authApiInterface.signUp(signUpRequest)
        val result = response.body()
        return try {
            when(response.code()){
                in 200..299 -> Resource.Success(result)
                in 400..499 ->{
                    val gson = Gson()
                    val type = object: TypeToken<ErrorData>() {}.type
                    val errorResponse: ErrorData = gson.fromJson(response.errorBody()?.charStream(), type)
                    Resource.Failure(errorResponse.error.message)
                }
                else -> {
                    Resource.Failure(response.message())
                }
            }
        }catch (e: Exception){
            Resource.Failure("An error occurred")
        }
    }

    override suspend fun signIn(loginRequest: LoginRequest): Resource<LoginResponse> {
        val response = authApiInterface.signIn(loginRequest)
        val result = response.body()
        return try {
            when(response.code()){
                in 200..299 -> Resource.Success(result)
                in 400..499 ->{
                    val gson = Gson()
                    val type = object: TypeToken<ErrorData>() {}.type
                    val errorResponse: ErrorData = gson.fromJson(response.errorBody()?.charStream(), type)
                    Resource.Failure(errorResponse.error.message)
                }
                else -> {
                    Resource.Failure(response.message())
                }
            }
        }catch (e: Exception){
            Resource.Failure("An error occurred $e")
        }
    }
}