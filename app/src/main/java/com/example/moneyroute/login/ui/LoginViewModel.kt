package com.example.moneyroute.login.ui

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moneyroute.R
import com.example.moneyroute.navigation.HomeGraph
import com.example.moneyroute.utilities.AuthManager
import com.example.moneyroute.utilities.AuthRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginEnable = MutableStateFlow(false)
    val loginEnable = _loginEnable.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    fun onLoginClicked(context: Context, authManager: AuthManager, scope: CoroutineScope, navController: NavController) {
        scope.launch {
            when (val result = authManager.signInWithEmailAndPassword(email.value, password.value)) {
                is AuthRes.Success -> {
                    Toast.makeText(context, R.string.toast_login_successfully, Toast.LENGTH_SHORT).show()
                }
                is AuthRes.Error -> {
                    Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
        navController.navigate(HomeGraph)
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length >= 8
}