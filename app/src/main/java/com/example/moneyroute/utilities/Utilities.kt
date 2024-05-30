package com.example.moneyroute.utilities

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Utilities {
    fun convertDateFromEpochMilliToLocalDate(milli: Long): LocalDate =
        Instant.ofEpochMilli(milli).atZone(ZoneId.of("UTC")).toLocalDate()

    fun getFormattedDate(localDate: LocalDate): String = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}