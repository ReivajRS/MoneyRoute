package com.example.moneyroute.movements.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.Periodicity
import com.example.moneyroute.movements.data.RepetitionType

@Composable
fun MovementList(
    modifier: Modifier = Modifier,
    movementList: List<Movement>
) {
    var selectedMovement: Movement? by remember { mutableStateOf(null) }
    LazyColumn(modifier = modifier) {
        items(movementList) { movement ->
            MovementCard(movement = movement, onCardClick = { selectedMovement = movement })
        }
    }
    selectedMovement?.let {
        MovementDescriptionDialog(movement = it, onDismiss = { selectedMovement = null })
    }
}

@Preview(showBackground = true)
@Composable
private fun MovementListPreview() {
    val movements = listOf(
        Movement(
            type = "Ingreso",
            category = "Salario",
            amount = 2000.00,
            description = "Pago de salario mensual",
            date = 1678901200000L, // 2023-03-15 00:00:00 GMT
            periodicity = Periodicity(
                description = "Mensual",
                repetitionType = RepetitionType.MONTHLY,
                interval = 1
            )
        ),
        Movement(
            type = "Gasto",
            category = "Comida",
            amount = 50.00,
            description = "Compra de alimentos",
            date = 1678987600000L, // 2023-03-15 12:00:00 GMT
            periodicity = null
        ),
        Movement(
            type = "Ingreso",
            category = "Intereses",
            amount = 10.00,
            description = "Intereses de la cuenta de ahorros",
            date = 1679074800000L, // 2023-03-16 00:00:00 GMT
            periodicity = Periodicity(
                description = "Mensual",
                repetitionType = RepetitionType.MONTHLY,
                interval = 1
            )
        ),
        Movement(
            type = "Gasto",
            category = "Transporte",
            amount = 20.00,
            description = "Pago de transporte público",
            date = 1679161200000L, // 2023-03-17 00:00:00 GMT
            periodicity = Periodicity(
                description = "Semanal",
                repetitionType = RepetitionType.WEEKLY,
                interval = 1
            )
        )
    )
    MovementList(movementList = movements)
}