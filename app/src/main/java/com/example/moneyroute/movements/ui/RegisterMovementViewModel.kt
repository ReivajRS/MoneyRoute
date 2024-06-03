package com.example.moneyroute.movements.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.R
import com.example.moneyroute.authentication.domain.GetCurrentUserUseCase
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.Periodicity
import com.example.moneyroute.movements.domain.AddMovementUseCase
import com.example.moneyroute.movements.domain.GetCategoriesUseCase
import com.example.moneyroute.movements.domain.GetMovementTypesUseCase
import com.example.moneyroute.movements.domain.GetPeriodicitiesUseCase
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
class RegisterMovementViewModel @Inject constructor(
    private val addMovementUseCase: AddMovementUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _movementState = MutableStateFlow(MovementState("", "", "", null, "", null))
    val movementState = _movementState.asStateFlow()

    private val _movementTypes = MutableStateFlow<List<String>>(emptyList())
    val movementTypes = _movementTypes.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _periodicities = MutableStateFlow<List<Periodicity>>(emptyList())
    val periodicities = _periodicities.asStateFlow()

    private val getCategoriesUseCase = GetCategoriesUseCase()
    private val getMovementTypesUseCase = GetMovementTypesUseCase()
    private val getPeriodicitiesUseCase = GetPeriodicitiesUseCase()

    init {
        loadCategories()
        loadMovementTypes()
        loadPeriodicities()
    }

    fun onAmountChange(amount: String) {
        _movementState.value = _movementState.value.copy(amount = amount)
    }

    private fun loadMovementTypes() {
        CoroutineScope(Dispatchers.IO).launch {
            _movementTypes.value = getMovementTypesUseCase()
        }
    }

    fun onMovementTypeSelected(selectedMovementType: String) {
        _movementState.value = _movementState.value.copy(movementType = selectedMovementType)
    }

    private fun loadCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            _categories.value = getCategoriesUseCase()
        }
    }

    fun onCategorySelected(selectedCategory: String) {
        _movementState.value = _movementState.value.copy(category = selectedCategory)
    }

    fun onDateSelected(selectedDate: Long?) {
        selectedDate?.let {
            _movementState.value = _movementState.value.copy(date = it)
        }
    }

    private fun loadPeriodicities() {
        CoroutineScope(Dispatchers.IO).launch {
            _periodicities.value = getPeriodicitiesUseCase()
        }
    }

    fun onPeriodicitySelected(selectedPeriodicity: Periodicity) {
        _movementState.value = movementState.value.copy(periodicity = selectedPeriodicity)
    }

    fun onDescriptionChange(description: String) {
        _movementState.value = _movementState.value.copy(description = description)
    }

    fun onAddClicked(context: Context, isPeriodical: Boolean) {
        if (!validateFields(context, isPeriodical)) {
            return
        }
        if (!Utilities.hasInternetConnection(context)) {
            Toast.makeText(context, R.string.toast_no_internet_connection, Toast.LENGTH_SHORT).show()
            return
        }
        val movement = Movement(
            id = UUID.randomUUID().toString(),
            userId = getCurrentUserUseCase.invoke(),
            amount = movementState.value.amount.toDouble(),
            type = movementState.value.movementType,
            category = movementState.value.category,
            date = movementState.value.date!!,
            description = movementState.value.description,
            periodicity = movementState.value.periodicity
        )
        addMovement(movement)
        Toast.makeText(context, R.string.toast_movement_added_successfully, Toast.LENGTH_SHORT).show()
    }

    private fun validateFields(context: Context, isPeriodical: Boolean): Boolean {
        if (_movementState.value.amount.isBlank()) {
            Toast.makeText(context, R.string.toast_enter_amount, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Utilities.isPositiveNumber(_movementState.value.amount)) {
            Toast.makeText(context, R.string.toast_enter_positive_amount, Toast.LENGTH_SHORT).show()
            return false
        }
        if (_movementState.value.movementType.isBlank()) {
            Toast.makeText(context, R.string.toast_select_movement_type, Toast.LENGTH_SHORT).show()
            return false
        }
        if (_movementState.value.category.isBlank()) {
            Toast.makeText(context, R.string.toast_select_category, Toast.LENGTH_SHORT).show()
            return false
        }
        if (_movementState.value.date == null) {
            Toast.makeText(context, R.string.toast_select_date, Toast.LENGTH_SHORT).show()
            return false
        }
        if (isPeriodical && _movementState.value.periodicity == null) {
            Toast.makeText(context, R.string.toast_select_periodicity, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun addMovement(movement: Movement) {
        CoroutineScope(Dispatchers.IO).launch {
            addMovementUseCase(movement)
        }
    }
}

data class MovementState(
    val amount: String,
    val movementType: String,
    val category: String,
    val date: Long?,
    val description: String,
    val periodicity: Periodicity?
)