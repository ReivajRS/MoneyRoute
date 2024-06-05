package com.example.moneyroute.goals.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.moneyroute.R
import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.goals.data.Goal

@Composable
fun ContributionList(
    modifier: Modifier = Modifier,
    contributionList: List<Contribution>,
    onDeleteClicked: (Contribution) -> Unit,
) {
    var selectedContribution: Contribution? by remember { mutableStateOf(null) }
    LazyColumn(modifier = modifier) {
        items(contributionList) { contribution ->
            ContributionCard(contribution = contribution, onCardClick = { selectedContribution = contribution })
        }
    }
    selectedContribution?.let {
        ContributionDialog(
            onDismiss = { selectedContribution = null },
            onDelete = { onDeleteClicked(it) },
            onEdit = { }
        )
    }
}