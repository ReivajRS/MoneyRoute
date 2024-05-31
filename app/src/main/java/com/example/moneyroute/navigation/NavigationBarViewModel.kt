package com.example.moneyroute.navigation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moneyroute.navigation.BottomNavigationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationBarViewModel : ViewModel() {
    private val _selectedItem = MutableStateFlow(0)
    val selectedItem = _selectedItem.asStateFlow()

    private val _items = MutableStateFlow(BottomNavigationItem.items)
    val items = _items.asStateFlow()

    fun onItemSelected(itemIndex: Int, navController: NavController) {
        _selectedItem.value = itemIndex
        navController.navigate(_items.value[_selectedItem.value].route)
    }
}