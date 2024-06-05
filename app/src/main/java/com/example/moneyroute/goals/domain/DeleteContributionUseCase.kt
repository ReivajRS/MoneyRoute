package com.example.moneyroute.goals.domain

import com.example.moneyroute.goals.data.GoalsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteContributionUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
) {
    suspend operator fun invoke(contributionId: String, goalId: String) =
        goalsRepository.deleteContribution(contributionId, goalId)

}