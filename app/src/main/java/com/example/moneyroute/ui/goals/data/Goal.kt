package com.example.moneyroute.ui.goals.data

data class Goal(
    val label: String,
    val goalAmount: Double,
    val startDate: Long,
    val goalDate: Long,
    val description: String
)