package com.example.moneyroute.movements.domain

import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.MovementsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddMovementUseCase
@Inject constructor(
    private val repository: MovementsRepository,
) {
    suspend operator fun invoke(movement: Movement) = repository.addMovement(movement)
}