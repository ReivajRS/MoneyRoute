package com.example.moneyroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.moneyroute.account.ui.AccountScreen
import com.example.moneyroute.account.ui.AccountViewModel
import com.example.moneyroute.authentication.login.ui.LoginScreen
import com.example.moneyroute.authentication.login.ui.LoginViewModel
import com.example.moneyroute.authentication.signup.ui.SignupScreen
import com.example.moneyroute.authentication.signup.ui.SignupViewModel
import com.example.moneyroute.components.BottomNavigationBar
import com.example.moneyroute.components.FabOption
import com.example.moneyroute.components.FloatingActionButtonWithMenu
import com.example.moneyroute.components.TitleTopBar
import com.example.moneyroute.goals.ui.GoalsScreen
import com.example.moneyroute.goals.ui.GoalsViewModel
import com.example.moneyroute.goals.ui.contribute.ContributeGoalScreen
import com.example.moneyroute.goals.ui.contribute.ContributeGoalViewModel
import com.example.moneyroute.goals.ui.register.RegisterGoalScreen
import com.example.moneyroute.goals.ui.register.RegisterGoalViewModel
import com.example.moneyroute.movements.ui.MovementsScreen
import com.example.moneyroute.movements.ui.MovementsViewModel
import com.example.moneyroute.movements.ui.RegisterMovementScreen
import com.example.moneyroute.movements.ui.RegisterMovementViewModel
import com.example.moneyroute.navigation.HomeGraph
import com.example.moneyroute.navigation.NavigationBarViewModel
import com.example.moneyroute.navigation.Screen
import com.example.moneyroute.queries.ui.QueriesScreen
import com.example.moneyroute.queries.ui.QueriesViewModel
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyRouteTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navigationBarViewModel = viewModel<NavigationBarViewModel>()

    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        topBar = {
            when (currentDestination) {
                Screen.Login.route -> {
                    TitleTopBar(title = stringResource(id = R.string.title_login))
                }

                Screen.Signup.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.button_signup),
                        backButton = true,
                        onBackArrowClick = { navController.popBackStack(Screen.Login, false) }
                    )
                }

                Screen.Home.route -> {
                    TitleTopBar(title = stringResource(id = R.string.app_name))
                }

                Screen.Movements.route -> {
                    TitleTopBar(title = stringResource(id = R.string.title_movements))
                }

                Screen.RegisterMovement.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_register_movement),
                        backButton = true,
                        onBackArrowClick = { navController.popBackStack(Screen.Movements, false) }
                    )
                }

                Screen.Goals.route -> {
                    TitleTopBar(title = stringResource(id = R.string.title_goals))
                }

                Screen.RegisterGoal.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_register_goal),
                        backButton = true,
                        onBackArrowClick = { navController.popBackStack(Screen.Goals, false) }
                    )
                }

                Screen.ContributeGoal.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_contribute_goal),
                        backButton = true,
                        onBackArrowClick = { navController.popBackStack(Screen.Goals, false) }
                    )
                }

                Screen.Queries.route -> {
                    TitleTopBar(title = stringResource(id = R.string.title_queries))
                }

                Screen.Account.route -> {
                    TitleTopBar(title = stringResource(id = R.string.title_account))
                }
            }
        },
        floatingActionButton = {
            when (currentDestination) {
                Screen.Movements.route -> {
                    FloatingActionButtonWithMenu(
                        fabOptions = listOf(
                            FabOption(
                                title = stringResource(id = R.string.title_register_movement),
                                onClick = {
                                    navController.navigate(
                                        Screen.RegisterMovement(
                                            isPeriodical = false
                                        )
                                    )
                                }
                            ),
                            FabOption(
                                title = stringResource(id = R.string.title_register_periodic_movement),
                                onClick = {
                                    navController.navigate(
                                        Screen.RegisterMovement(
                                            isPeriodical = true
                                        )
                                    )
                                }
                            )
                        ),
                        modifier = Modifier.padding(start = 160.dp)
                    )
                }

                Screen.Goals.route -> {
                    FloatingActionButtonWithMenu(
                        fabOptions = listOf(
                            FabOption(
                                title = stringResource(id = R.string.title_register_goal),
                                onClick = { navController.navigate(Screen.RegisterGoal) }
                            )
                        ),
                        modifier = Modifier.padding(start = 160.dp)
                    )
                }
            }
        },
        bottomBar = {
            if (currentDestination in listOf(
                    Screen.Home.route,
                    Screen.Movements.route,
                    Screen.Goals.route,
                    Screen.Queries.route,
                    Screen.Account.route
                )
            ) {
                BottomNavigationBar(
                    viewModel = navigationBarViewModel,
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Screen.Login> {
                val loginViewModel: LoginViewModel = hiltViewModel()
                LoginScreen(
                    viewModel = loginViewModel,
                    onLoginClicked = { result ->
                        if (result) { navController.navigate(HomeGraph) }
                    },
                    onWantToRegisterClicked = { navController.navigate(Screen.Signup) }
                )
            }
            composable<Screen.Signup> {
                val signupViewModel: SignupViewModel = hiltViewModel()
                SignupScreen(
                    viewModel = signupViewModel
                )
            }
            navigation<HomeGraph>(startDestination = Screen.Home) {
                composable<Screen.Home> {
                    val homeViewModel: HomeViewModel = hiltViewModel()
                    HomeScreen(
                        viewModel = homeViewModel,
                        onBack = { navController.popBackStack() }
                    )
                }
                composable<Screen.Movements> {
                    val movementsViewModel: MovementsViewModel = hiltViewModel()
                    MovementsScreen(viewModel = movementsViewModel)
                }
                composable<Screen.RegisterMovement> {
                    val args = it.toRoute<Screen.RegisterMovement>()
                    val registerMovementViewModel: RegisterMovementViewModel = hiltViewModel()
                    RegisterMovementScreen(
                        viewModel = registerMovementViewModel,
                        isPeriodical = args.isPeriodical
                    )
                }
                composable<Screen.Goals> {
                    val goalsViewModel: GoalsViewModel = hiltViewModel()
                    val context = LocalContext.current
                    GoalsScreen(
                        viewModel = goalsViewModel,
                        onContributeClicked = { goal ->
                            navController.navigate(
                                Screen.ContributeGoal(
                                    goalId = goal.id,
                                    goalLabel = goal.label,
                                    remainingAmount = (goal.goalAmount - goal.currentAmount).toString()
                                )
                            )
                        }
                    )
                }
                composable<Screen.RegisterGoal> {
                    val registerGoalViewModel: RegisterGoalViewModel = hiltViewModel()
                    RegisterGoalScreen(
                        viewModel = registerGoalViewModel
                    )
                }
                composable<Screen.ContributeGoal> {
                    val args = it.toRoute<Screen.ContributeGoal>()
                    val goalId = args.goalId
                    val goalLabel = args.goalLabel
                    val remainingAmount = args.remainingAmount
                    val contributeGoalViewModel: ContributeGoalViewModel = hiltViewModel()
                    ContributeGoalScreen(
                        viewModel = contributeGoalViewModel,
                        goalId = goalId,
                        goalLabel = goalLabel,
                        remainingAmount = remainingAmount
                    )
                }
                composable<Screen.Queries> {
                    val queriesViewModel: QueriesViewModel = hiltViewModel()
                    QueriesScreen(
                        viewModel = queriesViewModel,
                        onContributeClicked = { goal ->
                            navController.navigate(
                                Screen.ContributeGoal(
                                    goalId = goal.id,
                                    goalLabel = goal.label,
                                    remainingAmount = (goal.goalAmount - goal.currentAmount).toString()
                                )
                            )
                        }
                    )
                }
                composable<Screen.Account> {
                    val accountViewModel: AccountViewModel = hiltViewModel()
                    AccountScreen(viewModel = accountViewModel)
                }
            }
        }
    }
}