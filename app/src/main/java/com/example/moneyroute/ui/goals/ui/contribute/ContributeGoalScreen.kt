package com.example.moneyroute.ui.goals.ui.contribute

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.moneyroute.ui.components.AddButton
import com.example.moneyroute.ui.components.AmountField
import com.example.moneyroute.ui.components.CustomDatePicker
import com.example.moneyroute.ui.components.RowElement
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun ContributeGoalScreen(
    modifier: Modifier = Modifier,
    viewModel: ContributeGoalViewModel
) {
    MoneyRouteTheme {
        Scaffold(
            topBar = { ContributeGoalTopBar() }
        ) { innerPadding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ContributeGoal(
                    modifier = modifier.padding(innerPadding),
                    viewModel = viewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContributeGoalTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.title_contribute_goal),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun ContributeGoal(
    modifier: Modifier = Modifier,
    viewModel: ContributeGoalViewModel
) {
    val contributionState: ContributionState by viewModel.contributionState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        RowElement(text = stringResource(id = R.string.text_amount)) {
            AmountField(
                amount = contributionState.amount,
                onAmountChange = { viewModel.onAmountChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(text = stringResource(id = R.string.text_date)) {
            CustomDatePicker(
                selectedDate = contributionState.date,
                onDateSelected = { viewModel.onDateSelected(it) })
        }

        Spacer(modifier = Modifier.padding(40.dp))

        AddButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(160.dp),
            onAddClicked = { viewModel.onAddClicked(context) }
        )
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ContributeGoalScreenPreview() {
    MoneyRouteTheme {
        Scaffold(
            topBar = { ContributeGoalTopBar() }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ContributeGoal(
                    modifier = Modifier.padding(innerPadding),
                    viewModel = ContributeGoalViewModel()
                )
            }
        }
    }
}