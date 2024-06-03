package com.example.moneyroute.movements.data

data class Periodicity(
    val description: String = "",
    val repetitionType: RepetitionType = RepetitionType.NONE,
    val interval: Int = 0
)

enum class RepetitionType {
    DAILY, WEEKLY, MONTHLY, NONE
}