package com.example.moneyroute.movements.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.movements.domain.GetCategoriesUseCase
import com.example.moneyroute.movements.domain.GetMovementTypesUseCase
import com.example.moneyroute.movements.domain.GetPeriodicitiesUseCase
import com.example.moneyroute.movements.data.Periodicity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterMovementViewModel(
) : ViewModel() {

    private val _movementState = MutableStateFlow(MovementState("", "", "", null, "", null))
    val movementState = _movementState.asStateFlow()

    private val _movementTypes = MutableStateFlow<List<String>>(emptyList())
    val movementTypes = _movementTypes.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    // For periodic movement (Just in case that the user want to register a periodic movement)
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
        // TODO: Recuperar tipos de movimientos
        CoroutineScope(Dispatchers.IO).launch {
            _movementTypes.value = getMovementTypesUseCase()
        }
    }

    fun onMovementTypeSelected(selectedMovementType: String) {
        _movementState.value = _movementState.value.copy(movementType = selectedMovementType)
    }

    private fun loadCategories() {
        // TODO: Recuperar categorias
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

    fun onAddClicked(context: Context) {
        // TODO: VALIDAR LO INGRESADO Y AGREGAR A LA BD
        Toast.makeText(context, "Movimiento registrado exitosamente", Toast.LENGTH_SHORT).show()
    }
}

data class MovementState(
    var amount: String,
    var movementType: String,
    var category: String,
    var date: Long?,
    var description: String,
    var periodicity: Periodicity?
)