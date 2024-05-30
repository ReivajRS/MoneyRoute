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
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.ui.GoalCard
import com.example.moneyroute.goals.ui.GoalList
import com.example.moneyroute.goals.ui.register.RegisterGoalScreen
import com.example.moneyroute.goals.ui.register.RegisterGoalViewModel
import com.example.moneyroute.login.ui.LoginScreen
import com.example.moneyroute.login.ui.LoginViewModel
import com.example.moneyroute.movements.MovementList
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.Periodicity
import com.example.moneyroute.movements.data.RepetitionType
import com.example.moneyroute.movements.ui.RegisterMovementViewModel
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
//                    LoginScreen(modifier = Modifier.padding(innerPadding).fillMaxSize(), viewModel = loginViewModel)
//                    SignupScreen(modifier = Modifier.padding(innerPadding).fillMaxSize(), viewModel = signupViewModel)
//                    RegisterMovementScreen(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                            .fillMaxSize(),
//                        isPeriodicMovement = true,
//                        viewModel = registerMovementViewModel)
//                    RegisterGoalScreen(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                            .fillMaxSize(),
//                        viewModel = registerGoalViewModel
//                    )


                    val goals = listOf(
                        Goal(
                            label = "Save for a new car",
                            currentAmount = 5000.00,
                            goalAmount = 10000.00,
                            startDate = 1678901200000L, // 2023-03-15 00:00:00 GMT
                            goalDate = 1710437200000L, // 2023-09-15 00:00:00 GMT
                            description = "I want to save up for a new car by the end of the year.",
                            status = "In progress"
                        ),
                        Goal(
                            label = "Pay off credit card debt",
                            currentAmount = 2000.00,
                            goalAmount = 5000.00,
                            startDate = 1640997600000L, // 2022-01-01 00:00:00 GMT
                            goalDate = 1672533600000L, // 2023-01-01 00:00:00 GMT
                            description = "I want to pay off my credit card debt by the end of the year.",
                            status = "Completed"
                        ),
                        Goal(
                            label = "Invest in stocks",
                            currentAmount = 1000.00,
                            goalAmount = 10000.00,
                            startDate = 1678901200000L, // 2023-03-15 00:00:00 GMT
                            goalDate = 1741973200000L, // 2024-09-15 00:00:00 GMT
                            description = "I want to invest in stocks for the long term.",
                            status = "Not started"
                        ),
                        Goal(
                            label = "Ahorrar para un coche nuevo",
                            currentAmount = 5000.00,
                            goalAmount = 10000.00,
                            startDate = 1678901200000L, // 15 de marzo de 2023 00:00:00 GMT
                            goalDate = 1710437200000L, // 15 de septiembre de 2023 00:00:00 GMT
                            description = "Quiero ahorrar para un coche nuevo a finales de año.",
                            status = "En progreso"
                        ),
                        Goal(
                            label = "Pagar la deuda de la tarjeta de crédito",
                            currentAmount = 2000.00,
                            goalAmount = 5000.00,
                            startDate = 1640997600000L, // 1 de enero de 2022 00:00:00 GMT
                            goalDate = 1672533600000L, // 1 de enero de 2023 00:00:00 GMT
                            description = "Quiero pagar la deuda de mi tarjeta de crédito a finales de año.",
                            status = "Completado"
                        ),
                        Goal(
                            label = "Invertir en acciones",
                            currentAmount = 1000.00,
                            goalAmount = 10000.00,
                            startDate = 1678901200000L, // 15 de marzo de 2023 00:00:00 GMT
                            goalDate = 1741973200000L, // 15 de septiembre de 2024 00:00:00 GMT
                            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                            status = "No iniciado"
                        )
                    )
                    GoalList(modifier = Modifier.padding(innerPadding), goalList = goals)
                }
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    MoneyRouteTheme {
        Scaffold { innerPadding ->
            LoginScreen(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), viewModel = LoginViewModel())
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignupScreenPreview() {
    MoneyRouteTheme {
        Scaffold { innerPadding ->
//            LoginScreen(modifier = Modifier.padding(innerPadding).fillMaxSize(), viewModel = LoginViewModel())
//            SignupScreen(modifier = Modifier.padding(innerPadding).fillMaxSize(), viewModel = SignupViewModel())
//            RegisterMovementScreen(
//                modifier = Modifier.padding(innerPadding).fillMaxSize(),
//                isPeriodicMovement = true,
//                viewModel = RegisterMovementViewModel()
//            )
//            RegisterGoalScreen(
//                modifier = Modifier.padding(innerPadding),
//                viewModel = RegisterGoalViewModel()
//            )
//            GoalCard(
//                modifier = Modifier.padding(innerPadding),
//                goal = Goal(
//                    label = "Meta 1",
//                    currentAmount = 450.0,
//                    goalAmount = 1000.0,
//                    startDate = 1717039194000,
//                    goalDate = 1717539194000,
//                    description = "Descripcion de la meta numero 1",
//                    status = "Activa"
//                )
//            )
            val movements = listOf(
                Movement(
                    type = "Ingreso",
                    category = "Salario",
                    amount = 2000.00,
                    description = "Pago de salario mensual",
                    date = 1678901200000L, // 2023-03-15 00:00:00 GMT
                    periodicity = Periodicity(
                        description = "Mensual",
                        repetitionType = RepetitionType.MONTHLY,
                        interval = 1
                    )
                ),
                Movement(
                    type = "Gasto",
                    category = "Comida",
                    amount = 50.00,
                    description = "Compra de alimentos",
                    date = 1678987600000L, // 2023-03-15 12:00:00 GMT
                    periodicity = null
                ),
                Movement(
                    type = "Ingreso",
                    category = "Intereses",
                    amount = 10.00,
                    description = "Intereses de la cuenta de ahorros",
                    date = 1679074800000L, // 2023-03-16 00:00:00 GMT
                    periodicity = Periodicity(
                        description = "Mensual",
                        repetitionType = RepetitionType.MONTHLY,
                        interval = 1
                    )
                ),
                Movement(
                    type = "Gasto",
                    category = "Transporte",
                    amount = 20.00,
                    description = "Pago de transporte público",
                    date = 1679161200000L, // 2023-03-17 00:00:00 GMT
                    periodicity = Periodicity(
                        description = "Semanal",
                        repetitionType = RepetitionType.WEEKLY,
                        interval = 1
                    )
                )
            )
            MovementList(modifier = Modifier.padding(innerPadding), movementList = movements)
        }
    }
}