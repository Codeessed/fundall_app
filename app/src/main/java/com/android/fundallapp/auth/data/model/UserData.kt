package com.android.fundallapp.auth.data.model

data class UserData(
    val id: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val avatar: String,
    val monthly_target: String,
    val access_token: String,
    val token_type: String,
)
