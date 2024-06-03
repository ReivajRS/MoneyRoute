package com.example.moneyroute.queries.domain

class GetTabsUseCase() {
    private val tabs = listOf(
        Tab.Movements,
        Tab.Goals,
        Tab.Contributions
    )
    suspend operator fun invoke(): List<Tab> = tabs
}

sealed class Tab(
    val name: String,
    val ordinal: Int
) {

    data object Movements : Tab("Movimientos", 0)
    data object Goals : Tab("Metas", 1)
    data object Contributions : Tab("Abonos", 2)
}