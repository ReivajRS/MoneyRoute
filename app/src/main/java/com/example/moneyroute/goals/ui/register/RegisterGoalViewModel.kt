package com.example.moneyroute.goals.ui.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterGoalViewModel : ViewModel() {

    private val _goalState = MutableStateFlow(GoalState("", "", null, null, ""))
    val goalState = _goalState.asStateFlow()

    fun onLabelChange(label: String) {
        _goalState.value = _goalState.value.copy(label = label)
    }

    fun onGoalAmountChange(goalAmount: String) {
        _goalState.value = _goalState.value.copy(goalAmount = goalAmount)
    }

    fun onStartDateSelected(selectedStartDate: Long?) {
        selectedStartDate?.let {
            _goalState.value = _goalState.value.copy(startDate = it)
        }
    }

    fun onGoalDateSelected(selectedGoalDate: Long?) {
        selectedGoalDate?.let {
            _goalState.value = _goalState.value.copy(goalDate = it)
        }
    }

    fun onDescriptionChange(description: String) {
        _goalState.value = _goalState.value.copy(description = description)
    }

    fun onAddClicked(context: Context) {
        // TODO: VALIDAR LO INGRESADO Y AGREGAR A LA BD.
        // TODO: AGREGAR DISTINTOS TIPOS DE MENSAJES
        Toast.makeText(context, "Meta agregada exitosamente", Toast.LENGTH_SHORT).show()
    }
}

data class GoalState(
    var label: String,
    var goalAmount: String,
    var startDate: Long?,
    var goalDate: Long?,
    var description: String
)