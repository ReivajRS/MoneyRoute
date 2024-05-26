package com.example.moneyroute.ui.login.domain

class GetMovementTypesUseCase() {
    private val movementTypes = listOf("Ingreso", "Egreso")

    suspend operator fun invoke() = movementTypes
}