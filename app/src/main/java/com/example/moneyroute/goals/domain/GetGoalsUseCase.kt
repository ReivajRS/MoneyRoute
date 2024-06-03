package com.example.moneyroute.goals.domain

import com.example.moneyroute.goals.data.GoalsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGoalsUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    operator fun invoke(prefix: String, startDate: Long, endDate: Long, userId: String) =
        goalsRepository.getGoalList(prefix, startDate, endDate, userId)
}