package com.example.moneyroute.movements.data

data class Movement(
    var id: String = "",
    var userId: String = "",
    val type: String = "",
    val category: String = "",
    val amount: Double = 0.0,
    val description: String = "",
    val date: Long = 0,
    val periodicity: Periodicity? = null
)