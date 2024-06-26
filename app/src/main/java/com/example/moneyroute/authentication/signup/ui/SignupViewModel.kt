package com.example.moneyroute.authentication.signup.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.R
import com.example.moneyroute.authentication.data.User
import com.example.moneyroute.authentication.domain.AddUserUseCase
import com.example.moneyroute.authentication.domain.GetCurrentUserUseCase
import com.example.moneyroute.authentication.signup.domain.SignupUseCase
import com.example.moneyroute.utilities.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
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

    fun onSignupChange(firstName: String, lastName: String, email: String, password: String, confirmPassword: String) {
        _firstName.value = firstName
        _lastName.value = lastName
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword
    }

    fun onSignupClicked(context: Context) {
        if (!validateFields(context))
            return
        if (!Utilities.hasInternetConnection(context)) {
            Toast.makeText(context, R.string.toast_no_internet_connection, Toast.LENGTH_SHORT).show()
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            signupUseCase(_email.value, _password.value)
            addUserUseCase(
                User(
                    id = getCurrentUserUseCase.invoke(),
                    firstName = _firstName.value,
                    lastName = _lastName.value,
                    email = _email.value,
                    password = _password.value,
                    registerDate = System.currentTimeMillis()
                )
            )
        }
        Toast.makeText(context, R.string.toast_signup_successfully, Toast.LENGTH_SHORT).show()
    }

    private fun validateFields(context: Context): Boolean {
        if (_firstName.value.isBlank()) {
            Toast.makeText(context, R.string.toast_enter_first_name, Toast.LENGTH_SHORT).show()
            return false
        }
        if (_lastName.value.isBlank()) {
            Toast.makeText(context, R.string.toast_enter_last_name, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utilities.isValidEmail(_email.value)) {
            Toast.makeText(context, R.string.toast_enter_email, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utilities.isValidPassword(_password.value)) {
            Toast.makeText(context, R.string.toast_invalid_password, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utilities.isSamePassword(_password.value, _confirmPassword.value)) {
            Toast.makeText(context, R.string.toast_unmatched_passwords, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}