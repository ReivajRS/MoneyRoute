package com.example.moneyroute.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyroute.navigation.BottomNavigationItem
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    onItemClick: (Int) -> Unit,
    selectedItemIndex: Int
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            val selected = selectedItemIndex == index
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(index)
                },
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

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val items = BottomNavigationItem.items
    MoneyRouteTheme {
        BottomNavigationBar(items = items, onItemClick = {  }, selectedItemIndex = 0)
    }
}