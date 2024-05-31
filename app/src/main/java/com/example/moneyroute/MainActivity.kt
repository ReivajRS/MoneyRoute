package com.example.moneyroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyroute.goals.ui.register.RegisterGoalViewModel
import com.example.moneyroute.login.ui.LoginScreen
import com.example.moneyroute.login.ui.LoginViewModel
import com.example.moneyroute.movements.ui.RegisterMovementViewModel
import com.example.moneyroute.queries.ui.QueriesScreen
import com.example.moneyroute.queries.ui.QueriesViewModel
import com.example.moneyroute.signup.ui.SignupViewModel
import com.example.moneyroute.ui.theme.MoneyRouteTheme

class MainActivity : ComponentActivity() {
//    private val getCategoriesUseCase = GetCategoriesUseCase(CategoryRepository)

    private val loginViewModel by viewModels<LoginViewModel>()
    private val signupViewModel by viewModels<SignupViewModel>()
    private val registerMovementViewModel by viewModels<RegisterMovementViewModel>()
    private val registerGoalViewModel by viewModels<RegisterGoalViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyRouteTheme {
                Scaffold { innerPadding ->
                    QueriesScreen(modifier = Modifier.padding(innerPadding), viewModel = QueriesViewModel())
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MainScreenPreview() {
    MoneyRouteTheme {
        Scaffold { innerPadding ->
            LoginScreen(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), viewModel = LoginViewModel())
        }
    }
}