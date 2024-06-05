package com.example.moneyroute.goals.domain

import com.example.moneyroute.goals.data.GoalsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteGoalUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    suspend operator fun invoke(goalId: String) =
        goalsRepository.deleteGoal(goalId)
}