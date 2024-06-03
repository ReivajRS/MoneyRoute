package com.example.moneyroute.queries.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.R
import com.example.moneyroute.queries.domain.GetTabsUseCase
import com.example.moneyroute.utilities.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueriesViewModel @Inject constructor(
): ViewModel() {
    private val _tabs = MutableStateFlow(listOf(""))
    val tabs = _tabs.asStateFlow()

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab = _selectedTab.asStateFlow()

    private val _searchBarText = MutableStateFlow("")
    val searchBarText = _searchBarText.asStateFlow()

    private val _filterByDateState =
        MutableStateFlow(FilterByDateState())
    val filterByDateState = _filterByDateState.asStateFlow()

    val getTabsUseCase = GetTabsUseCase()

    init {
        loadTabs()
    }

    private fun loadTabs() {
        CoroutineScope(Dispatchers.IO).launch {
            _tabs.value = getTabsUseCase().map { tab -> tab.name }
        }
    }

    fun onTabSelected(selectedTab: Int) {
        _selectedTab.value = selectedTab
    }

    fun onSearchBarChange(text: String) {
        _searchBarText.value = text
    }

    fun onEnableChange() {
        _filterByDateState.value =
            if (_filterByDateState.value.enable) FilterByDateState()
            else _filterByDateState.value.copy(enable = true)
    }

    fun onDateRangeChange(startDate: Long?, endDate: Long?, context: Context) {
        if (startDate != null && endDate != null && !Utilities.isValidDateRange(startDate, endDate)) {
            Toast.makeText(context, R.string.toast_invalid_date_range, Toast.LENGTH_LONG).show()
            return
        }
        _filterByDateState.value = _filterByDateState.value.copy(
            startDate = startDate,
            endDate = endDate
        )
    }
}

data class FilterByDateState(
    val enable: Boolean = false,
    val startDate: Long? = null,
    val endDate: Long? = null
)