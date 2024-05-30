package com.example.moneyroute.signup.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignupViewModel : ViewModel() {
    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _signupEnable = MutableStateFlow(false)
    val signupEnable = _signupEnable.asStateFlow()

    fun onSignupChange(firstName: String, lastName: String, email: String, password: String, confirmPassword: String) {
        _firstName.value = firstName
        _lastName.value = lastName
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword
        _signupEnable.value = firstName.isNotBlank() && lastName.isNotBlank() && isValidEmail(email) && isValidPassword(password) && isSamePassword(password, confirmPassword)
    }

    fun onSignupClicked() {

    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length >= 8

    private fun isSamePassword(password: String, confirmPassword: String): Boolean {
        print(password)
        print(confirmPassword)
        return password == confirmPassword
    }
}