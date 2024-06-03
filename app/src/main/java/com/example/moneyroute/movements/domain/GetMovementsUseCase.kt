package com.example.moneyroute.movements.domain

import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.MovementsRepository
import com.example.moneyroute.utilities.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovementsUseCase @Inject constructor(
    private val movementsRepository: MovementsRepository
) {
    operator fun invoke(prefix: String, startDate: Long, endDate: Long, userId: String): Flow<Result<List<Movement>>> =
        movementsRepository.getMovementList(prefix, startDate, endDate, userId)
}