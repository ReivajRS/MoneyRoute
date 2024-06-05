package com.example.moneyroute.goals.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyroute.R
import com.example.moneyroute.authentication.domain.GetCurrentUserUseCase
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.domain.DeleteGoalUseCase
import com.example.moneyroute.goals.domain.GetGoalsUseCase
import com.example.moneyroute.utilities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val getGoalsUseCase: GetGoalsUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GoalListState())
    val state: State<GoalListState> = _state

    init {
        getGoalList()
    }

    fun getGoalList(prefix: String = "", startDate: Long = 0, endDate: Long = Long.MAX_VALUE) {
        getGoalsUseCase(prefix, startDate, endDate, getCurrentUserUseCase.invoke()).onEach { result ->
            when (result) {
                is Result.Error -> {
                    _state.value = GoalListState(error = result.message ?: "Error inesperado")
                }

                is Result.Loading -> {
                    _state.value = GoalListState(isLoading = true)
                }

                is Result.Success -> {
                    _state.value = GoalListState(goalList = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteGoal(context: Context, goalId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteGoalUseCase(goalId)
        }
        Toast.makeText(context, R.string.goal_deleted_successfully, Toast.LENGTH_SHORT).show()
    }
}

data class GoalListState(
    val isLoading: Boolean = false,
    val goalList: List<Goal> = emptyList(),
    val error: String = ""
)