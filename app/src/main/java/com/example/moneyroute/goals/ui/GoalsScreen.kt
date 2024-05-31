package com.example.moneyroute.goals.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.ui.register.RegisterGoalViewModel
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun GoalsScreen(
    modifier: Modifier = Modifier,
    registerGoalViewModel: RegisterGoalViewModel
) {
    GoalsContent(modifier = modifier)
}

@Composable
fun GoalsContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = 0.dp, start = 16.dp, end = 16.dp)
    ) {
//        Text(
//            text = stringResource(id = R.string.text_goals_list),
//            style = MaterialTheme.typography.bodyLarge,
//            fontWeight = FontWeight.Bold
//        )
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
        GoalsScreen(registerGoalViewModel = RegisterGoalViewModel())
    }
}