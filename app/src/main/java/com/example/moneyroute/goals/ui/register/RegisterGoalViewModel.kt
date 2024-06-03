package com.example.moneyroute.goals.ui.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.R
import com.example.moneyroute.authentication.domain.GetCurrentUserUseCase
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.goals.domain.AddGoalUseCase
import com.example.moneyroute.utilities.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RegisterGoalViewModel @Inject constructor(
    private val addGoalUseCase: AddGoalUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _goalState = MutableStateFlow(GoalState("", "", null, null, ""))
    val goalState = _goalState.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

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
        if (!validateFields(context)) {
            return
        }
        if (!Utilities.hasInternetConnection(context)) {
            Toast.makeText(context, R.string.toast_no_internet_connection, Toast.LENGTH_SHORT).show()
            return
        }
        val goal = Goal(
            id = UUID.randomUUID().toString(),
            userId = getCurrentUserUseCase.toString(),
            label = _goalState.value.label,
            goalAmount = _goalState.value.goalAmount.toDouble(),
            startDate = _goalState.value.startDate!!,
            goalDate = _goalState.value.goalDate!!,
            description = _goalState.value.description
        )
        addGoal(goal)
        Toast.makeText(context, R.string.toast_goal_added_successfully, Toast.LENGTH_SHORT).show()
    }

    private fun validateFields(context: Context): Boolean {
        if (_goalState.value.label.isBlank()) {
            Toast.makeText(context, R.string.toast_enter_label, Toast.LENGTH_SHORT).show()
            return false
        }
        if (_goalState.value.goalAmount.isBlank()) {
            Toast.makeText(context, R.string.toast_enter_amount, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utilities.isPositiveNumber(_goalState.value.goalAmount)) {
            Toast.makeText(context, R.string.toast_enter_positive_amount, Toast.LENGTH_SHORT).show()
            return false
        }
        if (_goalState.value.startDate == null) {
            Toast.makeText(context, R.string.toast_enter_start_date, Toast.LENGTH_SHORT).show()
            return false
        }
        if (_goalState.value.goalDate == null) {
            Toast.makeText(context, R.string.toast_enter_end_date, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utilities.isValidDateRange(startDate = _goalState.value.startDate!!, endDate = _goalState.value.goalDate!!)) {
            Toast.makeText(context, R.string.toast_invalid_date_range, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun addGoal(goal: Goal) {
        CoroutineScope(Dispatchers.IO).launch {
            addGoalUseCase(goal)
        }
    }
}

data class GoalState(
    val label: String,
    val goalAmount: String,
    val startDate: Long?,
    val goalDate: Long?,
    val description: String,
)