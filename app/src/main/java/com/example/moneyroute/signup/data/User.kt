package com.example.moneyroute.signup.data

data class User(
    var id: String = "",
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
