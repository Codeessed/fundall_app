package com.android.fundallapp.auth.data.model.signup

data class SignUpRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
)
