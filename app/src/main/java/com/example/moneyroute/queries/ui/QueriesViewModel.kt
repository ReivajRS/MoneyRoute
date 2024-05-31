package com.example.moneyroute.queries.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.domain.GetContributionsUseCase
import com.example.moneyroute.goals.domain.GetGoalsUseCase
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.domain.GetMovementsUseCase
import com.example.moneyroute.queries.domain.GetTabsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QueriesViewModel : ViewModel() {
    private val _tabs = MutableStateFlow<List<String>>(emptyList())
    val tabs = _tabs.asStateFlow()

    private val _movements = MutableStateFlow<List<Movement>>(emptyList())
    val movements = _movements.asStateFlow()

    private val _goals = MutableStateFlow<List<Goal>>(emptyList())
    val goals = _goals.asStateFlow()

    private val _contributions = MutableStateFlow<List<Contribution>>(emptyList())
    val contributions = _contributions.asStateFlow()

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab = _selectedTab.asStateFlow()

    private val _searchBarText = MutableStateFlow("")
    val searchBarText = _searchBarText.asStateFlow()

    private val _filterByDateState =
        MutableStateFlow(FilterByDateState(enable = false, startDate = null, endDate = null))
    val filterByDateState = _filterByDateState.asStateFlow()

    val getTabsUseCase = GetTabsUseCase()
    val getMovementsUseCase = GetMovementsUseCase()
    val getGoalsUseCase = GetGoalsUseCase()
    val getContributionsUseCase = GetContributionsUseCase()

    init {
        loadTabs()
        loadMovements()
        loadGoals()
        loadContributions()
    }

    private fun loadTabs() {
        // TODO: Recuperar opciones para consultar
        CoroutineScope(Dispatchers.IO).launch {
            _tabs.value = getTabsUseCase()
        }
    }

    fun onTabSelected(selectedTab: Int) {
        _selectedTab.value = selectedTab
    }

    private fun loadMovements() {
        // TODO: Recuperar opciones para consultar
        CoroutineScope(Dispatchers.IO).launch {
            _movements.value = getMovementsUseCase()
        }
    }

    private fun loadGoals() {
        // TODO: Recuperar opciones para consultar
        CoroutineScope(Dispatchers.IO).launch {
            _goals.value = getGoalsUseCase()
        }
    }

    private fun loadContributions() {
        // TODO: Recuperar opciones para consultar
        CoroutineScope(Dispatchers.IO).launch {
            _contributions.value = getContributionsUseCase()
        }
    }

    fun onSearchBarChange(text: String) {
        _searchBarText.value = text
    }

    fun onFilterByDateState(enable: Boolean, startDate: Long?, endDate: Long?) {
        _filterByDateState.value =
            if (enable) FilterByDateState(true, startDate, endDate)
            else FilterByDateState(false, null, null)
    }

    fun onEnableChange() {
        _filterByDateState.value =
            if (_filterByDateState.value.enable) FilterByDateState(false, null, null)
            else _filterByDateState.value.copy(enable = true)
    }

    fun onDateRangeChange(startDate: Long?, endDate: Long?, context: Context) {
        if (startDate != null && endDate != null && startDate > endDate) {
            Toast.makeText(context, "La fecha de fin debe ser mayor a la fecha de inicio", Toast.LENGTH_LONG).show()
            return
        }
        _filterByDateState.value = _filterByDateState.value.copy(startDate = startDate, endDate = endDate)
        // TODO: CAMBIAR LO MOSTRADO SEGUN EL RANGO DE FECHAS INGRESADO
    }


}

data class FilterByDateState(
    val enable: Boolean,
    val startDate: Long?,
    val endDate: Long?
)