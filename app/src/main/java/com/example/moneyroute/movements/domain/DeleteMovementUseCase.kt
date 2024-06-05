package com.example.moneyroute.movements.domain

import com.example.moneyroute.movements.data.MovementsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteMovementUseCase @Inject constructor(
    private val movementsRepository: MovementsRepository
) {
    suspend operator fun invoke(movementId: String) = movementsRepository.deleteMovement(movementId)
}