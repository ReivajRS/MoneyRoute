package com.example.moneyroute.goals.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyroute.goals.data.Goal

@Composable
fun GoalList(
    modifier: Modifier = Modifier,
    goalList: List<Goal>,
    onContributeClicked: (Goal) -> Unit
) {
    var selectedGoal: Goal? by remember { mutableStateOf(null) }
    LazyColumn(modifier = modifier) {
        items(goalList) { goal ->
            GoalCard(goal = goal, onCardClick = { selectedGoal = goal })
        }
    }
    selectedGoal?.let { goal ->
        GoalDescriptionDialog(
            goal = goal,
            onDismiss = { selectedGoal = null },
            onContribute = { onContributeClicked(goal) })
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun GoalListPreview() {
    val goals = listOf(
        Goal(
            label = "Save for a new car",
            currentAmount = 5000.00,
            goalAmount = 10000.00,
            startDate = 1678901200000L, // 2023-03-15 00:00:00 GMT
            goalDate = 1710437200000L, // 2023-09-15 00:00:00 GMT
            description = "I want to save up for a new car by the end of the year.",
            status = "In progress"
        ),
        Goal(
            label = "Pay off credit card debt",
            currentAmount = 2000.00,
            goalAmount = 5000.00,
            startDate = 1640997600000L, // 2022-01-01 00:00:00 GMT
            goalDate = 1672533600000L, // 2023-01-01 00:00:00 GMT
            description = "I want to pay off my credit card debt by the end of the year.",
            status = "Completed"
        ),
        Goal(
            label = "Invest in stocks",
            currentAmount = 1000.00,
            goalAmount = 10000.00,
            startDate = 1678901200000L, // 2023-03-15 00:00:00 GMT
            goalDate = 1741973200000L, // 2024-09-15 00:00:00 GMT
            description = "I want to invest in stocks for the long term.",
            status = "Not started"
        ),
        Goal(
            label = "Ahorrar para un coche nuevo",
            currentAmount = 5000.00,
            goalAmount = 10000.00,
            startDate = 1678901200000L, // 15 de marzo de 2023 00:00:00 GMT
            goalDate = 1710437200000L, // 15 de septiembre de 2023 00:00:00 GMT
            description = "Quiero ahorrar para un coche nuevo a finales de año.",
            status = "En progreso"
        ),
        Goal(
            label = "Pagar la deuda de la tarjeta de crédito",
            currentAmount = 2000.00,
            goalAmount = 5000.00,
            startDate = 1640997600000L, // 1 de enero de 2022 00:00:00 GMT
            goalDate = 1672533600000L, // 1 de enero de 2023 00:00:00 GMT
            description = "Quiero pagar la deuda de mi tarjeta de crédito a finales de año.",
            status = "Completado"
        ),
        Goal(
            label = "Invertir en acciones",
            currentAmount = 1000.00,
            goalAmount = 10000.00,
            startDate = 1678901200000L, // 15 de marzo de 2023 00:00:00 GMT
            goalDate = 1741973200000L, // 15 de septiembre de 2024 00:00:00 GMT
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            status = "No iniciado"
        )
    )
    GoalList(goalList = goals, onContributeClicked = { })
}