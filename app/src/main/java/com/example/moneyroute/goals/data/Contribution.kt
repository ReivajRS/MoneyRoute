package com.example.moneyroute.goals.data

data class Contribution(
    val id: String = "",
    val goalId: String = "",
    val goalLabel: String = "",
    val amount: Double = 0.0,
    val date: Long = 0
)
