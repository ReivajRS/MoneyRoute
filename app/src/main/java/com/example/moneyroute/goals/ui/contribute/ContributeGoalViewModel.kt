package com.example.moneyroute.goals.ui.contribute

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContributeGoalViewModel : ViewModel() {

    private val _contributionState = MutableStateFlow(ContributionState("", null))
    val contributionState = _contributionState.asStateFlow()

    fun onAmountChange(amount: String) {
        _contributionState.value = _contributionState.value.copy(amount = amount)
    }

    fun onDateSelected(selectedDate: Long?) {
        selectedDate?.let {
            _contributionState.value = _contributionState.value.copy(date = it)
        }
    }

    fun onAddClicked(context: Context) {
        // TODO: VALIDAR LO INGRESADO Y AGREGAR A LA BD
        Toast.makeText(context, "Abono realizado exitosamente", Toast.LENGTH_SHORT).show()
    }
}

data class ContributionState(
    var amount: String,
    var date: Long?
)