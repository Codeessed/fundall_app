package com.android.fundallapp.auth.domain.repository

import com.android.fundallapp.auth.data.model.login.LoginRequest
import com.android.fundallapp.auth.data.model.login.LoginResponse
import com.android.fundallapp.auth.data.model.signup.SignUpRequest
import com.android.fundallapp.auth.data.model.signup.SignUpResponse
import com.android.fundallapp.utils.Resource
import retrofit2.Response

interface AuthRepositoryImpl {

    suspend fun signUp(signUpRequest: SignUpRequest): Resource<SignUpResponse>

    suspend fun signIn(loginRequest: LoginRequest): Resource<LoginResponse>

}