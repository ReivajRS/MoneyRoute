package com.example.moneyroute.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Login: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.Login"
    }

    @Serializable
    data object Signup: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.Signup"
    }

    @Serializable
    data object Home: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.Home"
    }

    @Serializable
    data object Movements: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.Movements"
    }

    @Serializable
    data class RegisterMovement(
        val isPeriodical: Boolean
    ) : Screen() {
        companion object {
            val route = "com.example.moneyroute.navigation.Screen.RegisterMovement/{isPeriodical}"
        }
    }

    @Serializable
    data object Goals: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.Goals"
    }

    @Serializable
    data object RegisterGoal: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.RegisterGoal"
    }

    @Serializable
    data object ContributeGoal: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.ContributeGoal"
    }

    @Serializable
    data object Queries: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.Queries"
    }

    @Serializable
    data object Account: Screen() {
        val route =  "com.example.moneyroute.navigation.Screen.Account"
    }

    @Serializable
    data object RegisterAccount: Screen() {
    }
}