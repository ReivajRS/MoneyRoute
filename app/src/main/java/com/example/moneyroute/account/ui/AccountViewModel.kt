package com.example.moneyroute.account.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyroute.account.domain.GetUserAccountUseCase
import com.example.moneyroute.authentication.data.User
import com.example.moneyroute.authentication.domain.GetCurrentUserUseCase
import com.example.moneyroute.utilities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getUserAccountUseCase: GetUserAccountUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    val _state = mutableStateOf(UserAccountState())
    val state: State<UserAccountState> = _state

    init {
        getUserAccount()
    }

    fun getUserAccount() {
        getUserAccountUseCase(getCurrentUserUseCase.invoke()).onEach { result ->
            when (result) {
                is Result.Error -> {
                    _state.value = UserAccountState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = UserAccountState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = UserAccountState(userAccount = result.data ?: User())
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class UserAccountState(
    val isLoading: Boolean = false,
    val userAccount: User = User(),
    val error: String = ""
)