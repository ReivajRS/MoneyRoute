package com.example.moneyroute.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moneyroute.navigation.NavigationBarViewModel
import com.example.moneyroute.ui.theme.MoneyRouteTheme

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
                label = { Text(text = item.title, style = MaterialTheme.typography.bodySmall, fontSize = 10.sp) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    MoneyRouteTheme {
        BottomNavigationBar(
            viewModel = NavigationBarViewModel(),
            navController = NavController(LocalContext.current)
        )
    }
}