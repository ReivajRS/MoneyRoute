package com.example.moneyroute.authentication.login.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.R
import com.example.moneyroute.authentication.data.AuthRes
import com.example.moneyroute.authentication.data.User
import com.example.moneyroute.authentication.login.domain.LoginUseCase
import com.example.moneyroute.utilities.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
    }

    suspend fun onLoginClicked(context: Context): Boolean {
        if (!validateFields(context)) {
            return false
        }

        if (!Utilities.hasInternetConnection(context)) {
            Toast.makeText(context, R.string.toast_no_internet_connection, Toast.LENGTH_SHORT).show()
            return false
        }

        var success: Boolean

        withContext(Dispatchers.IO) {
            _isLoading.value = true
            success = loginUseCase(_email.value, _password.value) is AuthRes.Success
            _isLoading.value = false
        }

        withContext(Dispatchers.Main) {
            if (success) {
                Toast.makeText(context, R.string.toast_login_successfully, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, R.string.toast_login_failed, Toast.LENGTH_SHORT).show()
            }
        }

        return success
    }

    private fun validateFields(context: Context): Boolean {
        if (!Utilities.isValidEmail(email.value)) {
            Toast.makeText(context, R.string.toast_enter_email, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utilities.isValidPassword(password.value)) {
            Toast.makeText(context, R.string.toast_invalid_password, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}

data class LoginState(
    val user: User = User(),
    val isLoading: Boolean = false,
    val error: String = ""
)