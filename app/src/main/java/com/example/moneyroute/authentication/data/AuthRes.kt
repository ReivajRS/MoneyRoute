package com.example.moneyroute.authentication.data

sealed class AuthRes<out T> {
    data class Success<T>(val data: T): AuthRes<T>()
    data class Error(val message: String): AuthRes<Nothing>()
}