package com.example.moneyroute.ui.movements.domain

class GetMovementTypesUseCase() {
    private val movementTypes = listOf("Ingreso", "Egreso")

    suspend operator fun invoke() = movementTypes
}