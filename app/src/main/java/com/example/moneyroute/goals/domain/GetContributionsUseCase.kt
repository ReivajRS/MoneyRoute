package com.example.moneyroute.goals.domain

import com.example.moneyroute.goals.data.Contribution

class GetContributionsUseCase() {
    private val contributions = emptyList<Contribution>()

    suspend operator fun invoke(): List<Contribution> = contributions
}