package com.example.moneyroute.goals.ui.contribute

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.R
import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.goals.domain.ContributeGoalUseCase
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
class ContributeGoalViewModel @Inject constructor(
    private val contributeGoalUseCase: ContributeGoalUseCase
) : ViewModel() {

    private val _contributionState = MutableStateFlow(ContributionState(""))
    val contributionState = _contributionState.asStateFlow()

    fun onAmountChange(amount: String) {
        _contributionState.value = _contributionState.value.copy(amount = amount)
    }

    fun onAddClicked(context: Context, goalId: String, goalLabel: String, remainingAmount: String) {
        if (_contributionState.value.amount.isBlank()) {
            Toast.makeText(context, R.string.toast_enter_amount, Toast.LENGTH_SHORT).show()
            return
        }
        if (!Utilities.isPositiveNumber(_contributionState.value.amount)) {
            Toast.makeText(context, R.string.toast_enter_positive_amount, Toast.LENGTH_SHORT).show()
            return
        }
        if (_contributionState.value.amount.toDouble() > remainingAmount.toDouble()) {
            Toast.makeText(context, R.string.toast_remaining_amount_exceeded, Toast.LENGTH_SHORT).show()
            return
        }

        if (!Utilities.hasInternetConnection(context)) {
            Toast.makeText(context, R.string.toast_no_internet_connection, Toast.LENGTH_SHORT).show()
            return
        }

        val currentDate = System.currentTimeMillis()
        val contribution = Contribution(
            id = UUID.randomUUID().toString(),
            goalId = goalId,
            goalLabel = goalLabel,
            amount = _contributionState.value.amount.toDouble(),
            date = currentDate
        )

        CoroutineScope(Dispatchers.IO).launch {
            contributeGoalUseCase(contribution)
        }

        Toast.makeText(context, R.string.toast_contribution_added, Toast.LENGTH_SHORT).show()
    }
}

data class ContributionState(
    var amount: String = ""
)