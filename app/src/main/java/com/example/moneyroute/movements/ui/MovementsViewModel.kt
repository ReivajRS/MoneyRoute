package com.example.moneyroute.movements.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyroute.R
import com.example.moneyroute.authentication.domain.GetCurrentUserUseCase
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.domain.DeleteMovementUseCase
import com.example.moneyroute.movements.domain.GetMovementsUseCase
import com.example.moneyroute.utilities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovementsViewModel @Inject constructor(
    private val getMovementsUseCase: GetMovementsUseCase,
    private val deleteMovementUseCase: DeleteMovementUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel()
{
    private val _state = mutableStateOf(MovementListState())
    val state: State<MovementListState> = _state

    init {
        getMovementList()
    }

    fun getMovementList(prefix: String = "", startDate: Long = 0, endDate: Long = Long.MAX_VALUE) {
        getMovementsUseCase(prefix, startDate, endDate, getCurrentUserUseCase.invoke()).onEach { result ->
            when (result) {
                is Result.Error -> {
                    _state.value = MovementListState(error = result.message ?: "Error inesperado")
                }

                is Result.Loading -> {
                    _state.value = MovementListState(isLoading = true)
                }

                is Result.Success -> {
                    _state.value = MovementListState(movementList = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteMovement(context: Context, movement: Movement) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteMovementUseCase(movement.id)
        }
        Toast.makeText(context, R.string.movement_deleted, Toast.LENGTH_SHORT).show()
    }

}

data class MovementListState(
    val isLoading: Boolean = false,
    val movementList: List<Movement> = emptyList(),
    val error: String = ""
)