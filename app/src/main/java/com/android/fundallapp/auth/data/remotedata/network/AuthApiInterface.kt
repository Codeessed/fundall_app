package com.android.fundallapp.auth.data.remotedata.network

import com.android.fundallapp.auth.data.model.login.LoginRequest
import com.android.fundallapp.auth.data.model.login.LoginResponse
import com.android.fundallapp.auth.data.model.signup.SignUpRequest
import com.android.fundallapp.auth.data.model.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiInterface {

    @POST("register")
    suspend fun signUp(
        @Body
        signUpRequest: SignUpRequest
    ): Response<SignUpResponse>

    @POST("login")
    suspend fun signIn(
        @Body
        loginRequest: LoginRequest
    ): Response<LoginResponse>

}