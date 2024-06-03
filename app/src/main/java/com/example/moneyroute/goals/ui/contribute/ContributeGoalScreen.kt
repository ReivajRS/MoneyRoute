package com.example.moneyroute.goals.ui.contribute

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.AddButton
import com.example.moneyroute.components.AmountField
import com.example.moneyroute.components.RowElement

@Composable
fun ContributeGoalScreen(
    modifier: Modifier = Modifier,
    viewModel: ContributeGoalViewModel,
    goalId: String,
    goalLabel: String,
    remainingAmount: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ContributeGoal(
            modifier = Modifier,
            viewModel = viewModel,
            goalId = goalId,
            goalLabel = goalLabel,
            remainingAmount = remainingAmount
        )
    }
}

@Composable
fun ContributeGoal(
    modifier: Modifier = Modifier,
    viewModel: ContributeGoalViewModel,
    goalId: String,
    goalLabel: String,
    remainingAmount: String
) {
    val contributionState: ContributionState by viewModel.contributionState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        RowElement(text = stringResource(id = R.string.text_goal_label)) {
            TextField(
                value = goalLabel,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(text = stringResource(id = R.string.text_remaining_amount)) {
            TextField(
                value = "$${remainingAmount}",
                onValueChange = {},
                readOnly = true,
                enabled = false,
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(text = stringResource(id = R.string.text_amount)) {
            AmountField(
                amount = contributionState.amount,
                onAmountChange = { viewModel.onAmountChange(it) }
            )
        }

        Spacer(modifier = Modifier.padding(40.dp))

        AddButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(160.dp),
            onAddClicked = { viewModel.onAddClicked(context, goalId, goalLabel, remainingAmount) }
        )
    }
}