package com.android.fundallapp.auth.data.model.login

import com.android.fundallapp.auth.data.model.Message
import com.android.fundallapp.auth.data.model.User
import com.android.fundallapp.auth.data.model.UserData

data class LoginResponse(
    val success: User,
    val status: String,
)
