package com.example.moneyroute.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Patterns
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Utilities {
    fun convertDateFromEpochMilliToLocalDate(milli: Long): LocalDate =
        Instant.ofEpochMilli(milli).atZone(ZoneId.of("UTC")).toLocalDate()

    fun getFormattedDate(localDate: LocalDate): String = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))

    fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPassword(password: String): Boolean = password.length >= 8

    fun isPositiveNumber(input: String): Boolean =
        input.matches(Regex("\\d+(\\.\\d+)?$")) && input.toDouble() > 0

    fun isValidDateRange(startDate: Long, endDate: Long): Boolean = startDate <= endDate

    fun hasInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork
        val hasInternet = connectivityManager.getNetworkCapabilities(networkCapabilities)?.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        return hasInternet
    }
}