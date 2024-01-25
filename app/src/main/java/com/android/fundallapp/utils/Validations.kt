package com.android.fundallapp.utils

import com.google.android.material.textfield.TextInputLayout

object Validations {

    private val validate = ValidationFunctions


    fun validateEmailOnTextChanged(email: String, emailLayout: TextInputLayout){
        if(!validate.fieldIsNotEmpty(email)){
            emailLayout.error = "Email cannot be empty"
            emailLayout.errorIconDrawable = null
        }else{
            //to make sure it changes back to email cannot be empty if it's empty
            //first use
            if(!validate.emailIsValid(email)){
                emailLayout.error = "Email is not valid"
                emailLayout.errorIconDrawable = null
            }
            //2nd use
            if(validate.fieldIsNotEmpty(email) && validate.emailIsValid(email)){
                emailLayout.isErrorEnabled = false
            }
        }
    }

    fun validateFieldForName(field: String, fieldLayoutLayout: TextInputLayout){
        if(!validate.fieldIsNotEmpty(field)){
            fieldLayoutLayout.error = "Field cannot be empty"
            fieldLayoutLayout.errorIconDrawable = null
        }else{
            //to make sure it changes back to email cannot be empty if it's empty
            if(!validate.fieldIsGreaterThan2(field)){
                fieldLayoutLayout.error = "Field cannot be less than 3 characters"
                fieldLayoutLayout.errorIconDrawable = null
            }
            if(!validate.firstLetterIsValid(field)){
                fieldLayoutLayout.error = "Name cannot start with whitespace"
                fieldLayoutLayout.errorIconDrawable = null
            }
            if(validate.fieldIsNotEmpty(field) && validate.fieldIsGreaterThan2(field) && validate.firstLetterIsValid(field)){
                fieldLayoutLayout.isErrorEnabled = false
            }
        }
    }

    fun validateFieldLength(field: String, fieldLayoutLayout: TextInputLayout){
        if(!validate.fieldIsNotEmpty(field)){
            fieldLayoutLayout.error = "Field cannot be empty"
            fieldLayoutLayout.errorIconDrawable = null
        }else{
            if(!validate.characterLength(6, field)){
                fieldLayoutLayout.error = "Password must be at least 6 characters"
                fieldLayoutLayout.errorIconDrawable = null
            }else{
                fieldLayoutLayout.isErrorEnabled = false
            }
        }
    }

    fun validateEqualField(password: String, confirmPassword: String, fieldLayoutLayout: TextInputLayout){
        if(!validate.fieldIsNotEmpty(confirmPassword)){
            fieldLayoutLayout.error = "Field cannot be empty"
            fieldLayoutLayout.errorIconDrawable = null
        }else{
            if(!validate.fieldIsEqual(password, confirmPassword)){
                fieldLayoutLayout.error = "Passwords do not match"
                fieldLayoutLayout.errorIconDrawable = null
            }else{
                fieldLayoutLayout.isErrorEnabled = false
            }
        }
    }



}