package com.example.moneyroute.movements.domain

import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.Periodicity
import com.example.moneyroute.movements.data.RepetitionType

class GetMovementsUseCase() {
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

    suspend operator fun invoke(): List<Movement> = movements
}