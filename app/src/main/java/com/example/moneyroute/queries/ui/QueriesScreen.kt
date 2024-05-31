package com.example.moneyroute.queries.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.ui.GoalList
import com.example.moneyroute.movements.MovementList
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.queries.components.FilterByDateComponent
import com.example.moneyroute.queries.components.SearchBar
import com.example.moneyroute.queries.components.TabComponent
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun QueriesScreen(
    modifier: Modifier = Modifier,
    viewModel: QueriesViewModel,
) {
    Scaffold(
        topBar = { QueriesTopBar() },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Queries(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueriesTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(
                    onClick = { /*TODO: IR A LA PANTALLA PREVIA*/ },
                    modifier = modifier.align(Alignment.TopStart)
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "\n${
                        stringResource(id = R.string.title_queries)
                    }",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.align(Alignment.Center)
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun Queries(
    modifier: Modifier = Modifier,
    viewModel: QueriesViewModel
) {
    val tabs: List<String> by viewModel.tabs.collectAsState()
    val movements: List<Movement> by viewModel.movements.collectAsState()
    val goals: List<Goal> by viewModel.goals.collectAsState()
    val contributions: List<Contribution> by viewModel.contributions.collectAsState()
    val selectedTab: Int by viewModel.selectedTab.collectAsState()
    val searchBarText: String by viewModel.searchBarText.collectAsState()
    val filterByDateState: FilterByDateState by viewModel.filterByDateState.collectAsState()

    val context = LocalContext.current

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
            onStartDateSelected = { viewModel.onDateRangeChange(it, filterByDateState.endDate, context) },
            endDate = filterByDateState.endDate,
            onEndDateSelected = { viewModel.onDateRangeChange(filterByDateState.startDate, it, context) },
            modifier = Modifier.fillMaxWidth(),
        )

        // TODO: HACERLO GENERAL
        when (selectedTab) {
            0 -> MovementList(movementList = movements)
            1 -> GoalList(goalList = goals)
            2 -> {}
            else -> {}
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun QueriesScreenPreview() {
    MoneyRouteTheme {
        QueriesScreen(viewModel = QueriesViewModel())
    }
}