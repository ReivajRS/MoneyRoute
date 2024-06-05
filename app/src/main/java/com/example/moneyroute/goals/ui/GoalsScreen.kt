package com.example.moneyroute.goals.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.goals.components.GoalList
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.utilities.QueryFilter

@Composable
fun GoalsScreen(
    modifier: Modifier = Modifier,
    viewModel: GoalsViewModel,
    filter: QueryFilter? = null,
    showInformationText: Boolean = true,
    onContributeClicked: (Goal) -> Unit,
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(filter) {
        viewModel.getGoalList(
            prefix = filter?.prefix ?: "",
            startDate = filter?.startDate ?: 0,
            endDate = filter?.endDate ?: Long.MAX_VALUE
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        GoalsContent(
            modifier = modifier,
            showInformationText = showInformationText,
            goalList = state.goalList,
            onContributeClicked = { onContributeClicked(it) },
            onDeleteClicked = { viewModel.deleteGoal(context = context, it.id) },
            onEditClicked = {  }
        )
        if (state.error.isNotBlank()) {
            Text(text = stringResource(R.string.error_loading_data), modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
            Text(text = state.error, modifier = modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
        }
        if (state.isLoading) {
          CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun GoalsContent(
    modifier: Modifier = Modifier,
    showInformationText: Boolean = true,
    goalList: List<Goal>,
    onContributeClicked: (Goal) -> Unit,
    onDeleteClicked: (Goal) -> Unit,
    onEditClicked: (Goal) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        if (showInformationText) {
            Text(
                text = stringResource(R.string.text_goal_list),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        GoalList(
            goalList = goalList,
            onContributeClicked = { onContributeClicked(it) },
            onDeleteClicked = { onDeleteClicked(it) },
            onEditClicked = { onEditClicked(it) }
        )
    }
}