package com.example.moneyroute.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeGraph {
    override fun toString(): String = this.javaClass.canonicalName ?: "null"
}