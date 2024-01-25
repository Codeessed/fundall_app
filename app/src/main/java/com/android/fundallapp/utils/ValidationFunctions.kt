package com.android.fundallapp.utils

import android.util.Patterns

object ValidationFunctions {

    fun emailIsValid(email: String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun fieldIsNotEmpty(field: String): Boolean {
        return field.isNotEmpty()
    }

    fun fieldIsGreaterThan2(field: String): Boolean {
        return field.length >= 3
    }

    fun characterLength(length: Int, field: String): Boolean{
        return field.length >= length
    }

    fun fieldIsEqual(field1: String, field2: String): Boolean{
        return field1 == field2
    }

    fun firstLetterIsValid(text: String): Boolean{
        return text[0] != ' '
    }

}