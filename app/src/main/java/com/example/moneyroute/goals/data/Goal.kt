package com.example.moneyroute.goals.data

data class Goal(
    val label: String,
    val currentAmount: Double,
    val goalAmount: Double,
    val startDate: Long,
    val goalDate: Long,
    val description: String,
    val status: String
)