package com.example.moneyroute.goals.domain

import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.goals.data.GoalsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContributeGoalUseCase @Inject constructor(
    private val goalsRepository: GoalsRepository
){
    suspend operator fun invoke(contribution: Contribution){
        goalsRepository.contributeGoal(contribution)
    }

}