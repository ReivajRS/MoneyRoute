package com.example.moneyroute.goals.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyroute.R
import com.example.moneyroute.authentication.domain.GetCurrentUserUseCase
import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.goals.domain.DeleteContributionUseCase
import com.example.moneyroute.goals.domain.GetContributionsUseCase
import com.example.moneyroute.utilities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContributionsViewModel @Inject constructor(
    private val getContributionsUseCase: GetContributionsUseCase,
    private val deleteContributionUseCase: DeleteContributionUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ContributionListState())
    val state: State<ContributionListState> = _state

    fun getContributionList(prefix: String = "", startDate: Long = 0, endDate: Long = Long.MAX_VALUE) {
        getContributionsUseCase(prefix, startDate, endDate, getCurrentUserUseCase.invoke()).onEach { result ->
            when (result) {
                is Result.Error -> {
                    _state.value = ContributionListState(error = result.message ?: "Error inesperado")
                }

                is Result.Loading -> {
                    _state.value = ContributionListState(isLoading = true)
                }

                is Result.Success -> {
                    _state.value = ContributionListState(contributionList = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteContribution(context: Context, contribution: Contribution) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteContributionUseCase(contribution.id, contribution.goalId)
        }
        Toast.makeText(context, R.string.contribution_deleted, Toast.LENGTH_SHORT).show()
    }
}

data class ContributionListState(
    val isLoading: Boolean = false,
    val contributionList: List<Contribution> = emptyList(),
    val error: String = ""
)