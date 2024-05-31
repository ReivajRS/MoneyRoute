package com.example.moneyroute.queries.domain

class GetTabsUseCase() {
    private val tabs = listOf(
        "Movimientos",
        "Metas",
        "Abonos"
    )
    suspend operator fun invoke(): List<String> = tabs
}