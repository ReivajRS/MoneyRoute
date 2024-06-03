package com.example.moneyroute.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Graph {
    data object Root : Graph()
    data object Home : Graph()
    data object Auth : Graph()
}