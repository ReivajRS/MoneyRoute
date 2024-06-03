package com.example.moneyroute.queries.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.ui.ContributionsScreen
import com.example.moneyroute.goals.ui.ContributionsViewModel
import com.example.moneyroute.goals.ui.GoalsScreen
import com.example.moneyroute.goals.ui.GoalsViewModel
import com.example.moneyroute.movements.ui.MovementsScreen
import com.example.moneyroute.movements.ui.MovementsViewModel
import com.example.moneyroute.queries.components.FilterByDateComponent
import com.example.moneyroute.queries.components.SearchBar
import com.example.moneyroute.queries.components.TabComponent
import com.example.moneyroute.queries.domain.Tab
import com.example.moneyroute.utilities.QueryFilter

@Composable
fun QueriesScreen(
    modifier: Modifier = Modifier,
    viewModel: QueriesViewModel,
    onContributeClicked: (Goal) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Queries(viewModel = viewModel, onContributeClicked = { onContributeClicked(it) })
    }
}

@Composable
fun Queries(
    modifier: Modifier = Modifier,
    viewModel: QueriesViewModel,
    onContributeClicked: (Goal) -> Unit
) {
    val tabs: List<String> by viewModel.tabs.collectAsState()
    val selectedTab: Int by viewModel.selectedTab.collectAsState()
    val searchBarText: String by viewModel.searchBarText.collectAsState()
    val filterByDateState: FilterByDateState by viewModel.filterByDateState.collectAsState()

    val context = LocalContext.current

    val queryFilter = if (searchBarText.isNotBlank() || filterByDateState.enable) QueryFilter(
        searchBarText,
        filterByDateState.startDate ?: 0,
        filterByDateState.endDate ?: Long.MAX_VALUE
    ) else null

    Column(modifier = modifier) {
        TabComponent(tabs = tabs) { viewModel.onTabSelected(it) }

        SearchBar(
            text = searchBarText,
            onSearchBarChange = { viewModel.onSearchBarChange(it) }
        )

        FilterByDateComponent(
            checked = filterByDateState.enable,
            onCheckedChange = { viewModel.onEnableChange() },
            startDate = filterByDateState.startDate,
            onStartDateSelected = {
                viewModel.onDateRangeChange(
                    it,
                    filterByDateState.endDate,
                    context
                )
            },
            endDate = filterByDateState.endDate,
            onEndDateSelected = {
                viewModel.onDateRangeChange(
                    filterByDateState.startDate,
                    it,
                    context
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        when (selectedTab) {
            Tab.Movements.ordinal -> {
                val movementsViewModel: MovementsViewModel = hiltViewModel()
                MovementsScreen(
                    viewModel = movementsViewModel,
                    showInformationText = false,
                    filter = queryFilter
                )
            }
            Tab.Goals.ordinal -> {
                val goalsViewModel: GoalsViewModel = hiltViewModel()
                GoalsScreen(
                    viewModel = goalsViewModel,
                    showInformationText = false,
                    filter = queryFilter,
                    onContributeClicked = { onContributeClicked(it) }
                )
            }
            Tab.Contributions.ordinal -> {
                val contributionsViewModel: ContributionsViewModel = hiltViewModel()
                ContributionsScreen(
                    viewModel = contributionsViewModel,
                    showInformationText = false,
                    filter = queryFilter
                )
            }
        }
    }
}