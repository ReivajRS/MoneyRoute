package com.example.moneyroute.goals.domain

import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.data.GoalsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddGoalUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    suspend operator fun invoke(goal: Goal) = goalsRepository.addGoal(goal)
}