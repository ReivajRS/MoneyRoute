package com.example.moneyroute.movements.data

data class Periodicity(
    val description: String,
    val repetitionType: RepetitionType,
    val interval: Int
)

enum class RepetitionType {
    DAILY, WEEKLY, MONTHLY
}