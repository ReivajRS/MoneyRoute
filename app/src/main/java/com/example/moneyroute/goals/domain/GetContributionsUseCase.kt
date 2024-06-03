package com.example.moneyroute.goals.domain

import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.goals.data.GoalsRepository
import com.example.moneyroute.utilities.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetContributionsUseCase @Inject constructor(
//    private val contributionsRepository: ContributionsRepository
    private val goalsRepository: GoalsRepository
) {
//    private val contributions = emptyList<Contribution>()

    operator fun invoke(prefix: String, startDate: Long, endDate: Long, userId: String): Flow<Result<List<Contribution>>> =
        goalsRepository.getContributionList(prefix, startDate, endDate, userId)
}