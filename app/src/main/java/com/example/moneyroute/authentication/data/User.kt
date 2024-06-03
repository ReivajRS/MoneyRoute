package com.example.moneyroute.authentication.data

data class User(
    val id: String = "",
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val registerDate: Long = 0
)