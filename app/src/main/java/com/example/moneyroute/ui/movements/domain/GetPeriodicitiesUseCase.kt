package com.example.moneyroute.ui.movements.domain

import com.example.moneyroute.ui.movements.data.Periodicity
import com.example.moneyroute.ui.movements.data.RepetitionType

class GetPeriodicitiesUseCase() {
    // TODO: AGREGAR Y RECUPERAR LAS PERIODICIDADES DE LA BD
    private val periodicities = listOf(
        Periodicity("Cada día", RepetitionType.DAILY, 1),
        Periodicity("Cada semana", RepetitionType.WEEKLY, 1),
        Periodicity("Cada dos semanas", RepetitionType.WEEKLY, 2),
        Periodicity("Cada mes", RepetitionType.MONTHLY, 1),
        Periodicity("Cada dos meses", RepetitionType.MONTHLY, 2),
        Periodicity("Cada seis meses", RepetitionType.MONTHLY, 3),
    )
    suspend operator fun invoke(): List<Periodicity> = periodicities
}