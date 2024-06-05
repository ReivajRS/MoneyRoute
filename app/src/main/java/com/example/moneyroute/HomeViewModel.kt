package com.example.moneyroute

import androidx.lifecycle.ViewModel
import com.example.moneyroute.authentication.login.domain.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    fun logout() {
        logoutUseCase
    }
}