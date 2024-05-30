package com.example.moneyroute.movements.data

data class Movement(
    val type: String,
    val category: String,
    val amount: Double,
    val description: String,
    val date: Long,
    val periodicity: Periodicity?
)