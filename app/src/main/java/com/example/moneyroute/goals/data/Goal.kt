package com.example.moneyroute.goals.data

data class Goal(
    val id: String = "",
    val userId: String = "",
    val label: String = "",
    val currentAmount: Double = 0.0,
    val goalAmount: Double = 0.0,
    val startDate: Long = 0,
    val goalDate: Long = Long.MAX_VALUE,
    val description: String = "",
    val status: String = Status.active.status
)

sealed class Status(val status: String) {
    data object active : Status(status = "Activa")
    data object achieved : Status(status = "Lograda")
    data object unachieved : Status(status = "No lograda")
}