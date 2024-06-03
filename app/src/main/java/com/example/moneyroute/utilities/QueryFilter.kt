package com.example.moneyroute.utilities

data class QueryFilter(
    val prefix: String = "",
    val startDate: Long = 0,
    val endDate: Long = Long.MAX_VALUE
)