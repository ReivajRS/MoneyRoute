package com.example.moneyroute.movements.domain

class GetMovementTypesUseCase() {
    private val movementTypes = listOf("Ingreso", "Egreso")

    suspend operator fun invoke() = movementTypes
}