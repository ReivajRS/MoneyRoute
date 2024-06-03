package com.example.moneyroute.goals.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moneyroute.goals.data.Contribution

@Composable
fun ContributionList(
    modifier: Modifier = Modifier,
    contributionList: List<Contribution>
) {
    LazyColumn(modifier = modifier) {
        items(contributionList) { contribution ->
            ContributionCard(contribution = contribution)
        }
    }
}