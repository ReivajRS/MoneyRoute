package com.example.moneyroute.signup.ui

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.R
import com.example.moneyroute.signup.data.User
import com.example.moneyroute.utilities.AuthManager
import com.example.moneyroute.utilities.AuthRes
import com.example.moneyroute.utilities.FirebaseManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

    fun onSignupClicked(context: Context, authManager: AuthManager, firebaseManager: FirebaseManager, scope: CoroutineScope) {
        scope.launch {
            val result = authManager.createUserWithEmailAndPassword(_email.value, _password.value)
            when (result){
                is AuthRes.Success -> {
                    Toast.makeText(context, R.string.toast_signup_successfully, Toast.LENGTH_SHORT).show()
                }
                is AuthRes.Error -> {
                    Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
            firebaseManager.addUser(
                User(
                    id = firebaseManager.userId!!,
                    email = _email.value,
                    password = _password.value,
                    firstName = _firstName.value,
                    lastName = _lastName.value
                )
            )
        }
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length >= 8

    private fun isSamePassword(password: String, confirmPassword: String): Boolean {
        print(password)
        print(confirmPassword)
        return password == confirmPassword
    }
}