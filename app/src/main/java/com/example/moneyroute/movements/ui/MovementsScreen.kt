package com.example.moneyroute.movements.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.movements.components.MovementList
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.utilities.QueryFilter

@Composable
fun MovementsScreen(
    modifier: Modifier = Modifier,
    showInformationText: Boolean = true,
    viewModel: MovementsViewModel,
    filter: QueryFilter? = null
) {
    val state by viewModel.state
    val context = LocalContext.current

    LaunchedEffect(filter) {
        viewModel.getMovementList(
            prefix = filter?.prefix ?: "",
            startDate = filter?.startDate ?: 0,
            endDate = filter?.endDate ?: Long.MAX_VALUE
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        MovementsContent(
            modifier = modifier,
            showInformationText = showInformationText,
            movementList = state.movementList,
            onDeleteClicked = { viewModel.deleteMovement(context, it) },
            onEditClicked = {  }
        )

        if (state.error.isNotBlank()) {
            Text(text = stringResource(id = R.string.error_loading_data), modifier = modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
//            Text(text = state.error, modifier = modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun MovementsContent(
    modifier: Modifier = Modifier,
    showInformationText: Boolean = true,
    movementList: List<Movement>,
    onDeleteClicked: (Movement) -> Unit,
    onEditClicked: (Movement) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        if (showInformationText) {
            Text(
                text = stringResource(id = R.string.text_latest_movements),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        MovementList(movementList = movementList, onDeleteClicked = { onDeleteClicked(it) }, onEditClicked = { onEditClicked(it) })
    }
}