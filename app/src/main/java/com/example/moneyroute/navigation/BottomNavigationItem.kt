package com.example.moneyroute.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Any
) {
    // TODO: VER PARA CAMBIAR LOS NOMBRES
    data object HomeItem : BottomNavigationItem(
        title = "HomeItem",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = Screen.Home
    )
    data object MovementsItem : BottomNavigationItem(
        title = "Moves",
        selectedIcon = Icons.Filled.AddCircle,
        unselectedIcon = Icons.Outlined.AddCircle,
        route = Screen.Movements
    )
    data object GoalsItem : BottomNavigationItem(
        title = "Metas",
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
        route = Screen.Goals
    )
    data object QueriesItem : BottomNavigationItem(
        title = "Consultas",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        route = Screen.Queries
    )

    data object AccountItem : BottomNavigationItem(
        title = "Cuenta",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        route = Screen.Account
    )

    companion object {
        val items = listOf(HomeItem, MovementsItem, GoalsItem, QueriesItem, AccountItem)
    }

}