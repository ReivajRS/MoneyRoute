package com.example.moneyroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyroute.ui.login.ui.LoginScreen
import com.example.moneyroute.ui.login.ui.LoginViewModel
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyRouteTheme {
                Scaffold { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding).fillMaxSize(), viewModel = loginViewModel)
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    MoneyRouteTheme {
        Scaffold { innerPadding ->
            LoginScreen(modifier = Modifier.padding(innerPadding).fillMaxSize(), viewModel = LoginViewModel())
        }
    }
}