package com.example.moneyroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.moneyroute.components.FabOption
import com.example.moneyroute.components.FloatingActionButtonWithMenu
import com.example.moneyroute.components.TitleTopBar
import com.example.moneyroute.goals.ui.GoalsScreen
import com.example.moneyroute.goals.ui.contribute.ContributeGoalScreen
import com.example.moneyroute.goals.ui.contribute.ContributeGoalViewModel
import com.example.moneyroute.goals.ui.register.RegisterGoalScreen
import com.example.moneyroute.goals.ui.register.RegisterGoalViewModel
import com.example.moneyroute.login.ui.LoginScreen
import com.example.moneyroute.login.ui.LoginViewModel
import com.example.moneyroute.movements.ui.MovementsScreen
import com.example.moneyroute.movements.ui.RegisterMovementScreen
import com.example.moneyroute.movements.ui.RegisterMovementViewModel
import com.example.moneyroute.navigation.HomeGraph
import com.example.moneyroute.navigation.NavigationBarViewModel
import com.example.moneyroute.navigation.Screen
import com.example.moneyroute.queries.ui.QueriesScreen
import com.example.moneyroute.queries.ui.QueriesViewModel
import com.example.moneyroute.signup.ui.SignupScreen
import com.example.moneyroute.signup.ui.SignupViewModel
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import com.example.moneyroute.utilities.AuthManager
import com.example.moneyroute.utilities.FirebaseManager

class MainActivity : ComponentActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()
    private val signupViewModel by viewModels<SignupViewModel>()
    private val registerMovementViewModel by viewModels<RegisterMovementViewModel>()
    private val registerGoalViewModel by viewModels<RegisterGoalViewModel>()
    private val contributeGoalViewModel by viewModels<ContributeGoalViewModel>()
    private val queriesViewModel by viewModels<QueriesViewModel>()
    private val navigationBarViewModel by viewModels<NavigationBarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyRouteTheme {
                MainScreen(
                    loginViewModel = loginViewModel,
                    signupViewModel = signupViewModel,
                    registerMovementViewModel = registerMovementViewModel,
                    registerGoalViewModel = registerGoalViewModel,
                    contributeGoalViewModel =  contributeGoalViewModel,
                    queriesViewModel = queriesViewModel,
                    navigationBarViewModel = navigationBarViewModel
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel,
    registerMovementViewModel: RegisterMovementViewModel,
    registerGoalViewModel: RegisterGoalViewModel,
    contributeGoalViewModel: ContributeGoalViewModel,
    queriesViewModel: QueriesViewModel,
    navigationBarViewModel: NavigationBarViewModel
) {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        topBar = {
            when (currentDestination) {
                Screen.Login.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_login),
                        backButton = false,
                        onBackArrowClick = { }
                    )
                }
                Screen.Signup.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.button_signup),
                        backButton = false,
                        onBackArrowClick = { }
                    )
                }
                Screen.Home.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.app_name),
                        backButton = false,
                        onBackArrowClick = { }
                    )
                }
                Screen.RegisterMovement.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_register_movement),
                        backButton = true,
                        onBackArrowClick = { }
                    )
                }
                Screen.Goals.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_goals),
                        backButton = false,
                        onBackArrowClick = { }
                    )
                }
                Screen.RegisterGoal.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_register_goal),
                        backButton = true,
                        onBackArrowClick = { }
                    )
                }
                Screen.ContributeGoal.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_contribute_goal),
                        backButton = true,
                        onBackArrowClick = { }
                    )
                }
                Screen.Queries.route -> {
                    TitleTopBar(
                        title = stringResource(id = R.string.title_queries),
                        backButton = false,
                        onBackArrowClick = { }
                    )
                }
                // TODO: AGREGAR LA OPCION DE CUENTA
            }
        },
        floatingActionButton = {
            when (currentDestination) {
                Screen.Movements.route -> {
                    FloatingActionButtonWithMenu(
                        fabOptions = listOf(
                            FabOption(
                                title = stringResource(id = R.string.title_register_movement),
                                onClick = { navController.navigate(Screen.RegisterMovement(isPeriodical = false)) }
                            ),
                            FabOption(
                                title = stringResource(id = R.string.title_register_periodic_movement),
                                onClick = { navController.navigate(Screen.RegisterMovement(isPeriodical = true)) }
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
                    Screen.Queries.route
                )
            ) {
                BottomNavigationBar(
                    viewModel = navigationBarViewModel,
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        val authManager = AuthManager(LocalContext.current)
        val firebaseManager = FirebaseManager(context = LocalContext.current)

        NavHost(
            navController = navController,
            startDestination = Screen.Login,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Screen.Login> {
                LoginScreen(
                    viewModel = loginViewModel,
                    authManager = authManager,
                    navController = navController,
                    onLoginSuccess = { navController.navigate(HomeGraph) },
                    onWantToRegisterClicked = { navController.navigate(Screen.Signup) }
                )
            }
            composable<Screen.Signup> {
                SignupScreen(
                    viewModel = signupViewModel,
                    authManager = authManager,
                    firebaseManager = firebaseManager,
                    onBackArrowClicked = {
                        navController.popBackStack(route = Screen.Login, inclusive = false)
                    }
                )
            }
            navigation<HomeGraph>(startDestination = Screen.Home) {
                composable<Screen.Home> {
                    HomeScreen()
                }
                composable<Screen.Movements> {
                    MovementsScreen(firebaseManager = firebaseManager)
                }
                composable<Screen.RegisterMovement> {
                    val args = it.toRoute<Screen.RegisterMovement>()
                    RegisterMovementScreen(
                        viewModel = registerMovementViewModel,
                        firebaseManager = firebaseManager,
                        isPeriodical = args.isPeriodical
                    )
                }
                composable<Screen.Goals> {
                    GoalsScreen(
                        registerGoalViewModel = registerGoalViewModel,
                    )
                }
                composable<Screen.RegisterGoal> {
                    RegisterGoalScreen(
                        viewModel = registerGoalViewModel
                    )
                }
                composable<Screen.ContributeGoal> {
                    ContributeGoalScreen(
                        viewModel = contributeGoalViewModel
                    )
                }
                composable<Screen.Queries> {
                    QueriesScreen(viewModel = queriesViewModel)
                }

            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    viewModel: NavigationBarViewModel,
    navController: NavController
) {
    val selectedItem by viewModel.selectedItem.collectAsState()
    val items by viewModel.items.collectAsState()
    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            val selected = selectedItem == index
            NavigationBarItem(
                selected = selected,
                onClick = { viewModel.onItemSelected(index, navController) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) }
            )
        }
    }
}