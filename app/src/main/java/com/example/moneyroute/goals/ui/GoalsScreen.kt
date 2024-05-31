package com.example.moneyroute.goals.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.FloatingActionButtonWithMenu
import com.example.moneyroute.components.TitleTopBar
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun GoalsScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TitleTopBar(
                title = stringResource(id = R.string.title_goals),
                onBackArrowClick = { /* TODO: Regresar a la pantalla anterior */ }
            )
        },
        floatingActionButton = {
            FloatingActionButtonWithMenu(
                onRegisterMovementClick = { /*TODO*/ },
                onRegisterPeriodicMovementClick = {  },
                modifier = Modifier.padding(start = 120.dp)
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        GoalsContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun GoalsContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.text_goals_list),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        // TODO: QUITAR
        GoalList(goalList = listOf(
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            Goal("Meta ---", 15.0, 100.0, 1000000000000, 1500000000000, "Descripcion random", "Activa"),
            )
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun GoalsScreenPreview() {
    MoneyRouteTheme {
        GoalsScreen()
    }
}