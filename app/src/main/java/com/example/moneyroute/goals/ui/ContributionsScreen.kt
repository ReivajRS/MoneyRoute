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
import com.example.moneyroute.R
import com.example.moneyroute.goals.components.ContributionList
import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.utilities.QueryFilter

@Composable
fun ContributionsScreen(
    modifier: Modifier = Modifier,
    viewModel: ContributionsViewModel,
    filter: QueryFilter? = null,
    showInformationText: Boolean = true,
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(filter) {
        viewModel.getContributionList(
            prefix = filter?.prefix ?: "",
            startDate = filter?.startDate ?: 0,
            endDate = filter?.endDate ?: Long.MAX_VALUE
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        ContributionsContent(
            modifier = modifier,
            showInformationText = showInformationText,
            contributionList = state.contributionList,
            onDeleteClicked = { viewModel.deleteContribution(context, it) }
        )
        if (state.error.isNotBlank()) {
            Text(text = stringResource(R.string.error_loading_data), modifier = Modifier.align(
                Alignment.Center), color = MaterialTheme.colorScheme.error)
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ContributionsContent(
    modifier: Modifier = Modifier,
    showInformationText: Boolean = true,
    contributionList: List<Contribution>,
    onDeleteClicked: (Contribution) -> Unit,
) {
    Column(
        modifier = modifier.padding()
    ) {
        if (showInformationText) {
            Text(
                text = stringResource(R.string.text_contribution_list),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        ContributionList(contributionList = contributionList, onDeleteClicked = { onDeleteClicked(it) })
    }
}