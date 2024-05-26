package com.example.moneyroute.ui.movements.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.moneyroute.ui.login.domain.GetCategoriesUseCase
import com.example.moneyroute.ui.login.domain.GetMovementTypesUseCase
import com.example.moneyroute.ui.login.domain.GetPeriodicitiesUseCase
import com.example.moneyroute.ui.login.domain.Periodicity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterMovementViewModel(
) : ViewModel() {
    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _movementTypes = MutableStateFlow<List<String>>(emptyList())
    val movementTypes = _movementTypes.asStateFlow()

    private val _selectedMovementType = MutableStateFlow("")
    val selectedMovementType = _selectedMovementType.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _selectedDate = MutableStateFlow<Long?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    // For periodic movement (Just in case that the user want to register a periodic movement)
    private val _periodicities = MutableStateFlow<List<Periodicity>>(emptyList())
    val periodicities = _periodicities.asStateFlow()

    private val _selectedPeriodicity = MutableStateFlow<Periodicity?>(null)
    val selectedPeriodicity = _selectedPeriodicity.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val getCategoriesUseCase = GetCategoriesUseCase()
    private val getMovementTypesUseCase = GetMovementTypesUseCase()
    private val getPeriodicitiesUseCase = GetPeriodicitiesUseCase()

    init {
        loadCategories()
        loadMovementTypes()
        loadPeriodicities()
    }

    fun onAmountChange(amount: String) {
        _amount.value = amount
    }

    private fun loadMovementTypes() {
        // TODO: Recuperar tipos de movimientos
        CoroutineScope(Dispatchers.IO).launch {
            _movementTypes.value = getMovementTypesUseCase()
        }
    }

    fun onMovementTypeSelected(selectedMovementType: String) {
        _selectedMovementType.value = selectedMovementType
    }

    private fun loadCategories() {
        // TODO: Recuperar categorias
        CoroutineScope(Dispatchers.IO).launch {
            _categories.value = getCategoriesUseCase()
        }
    }

    fun onCategorySelected(selectedCategory: String) {
        _selectedCategory.value = selectedCategory
    }

    fun onDateSelected(selectedDate: Long?) {
        selectedDate?.let { _selectedDate.value = it }
    }

    private fun loadPeriodicities() {
        CoroutineScope(Dispatchers.IO).launch {
            _periodicities.value = getPeriodicitiesUseCase()
        }
    }

    fun onPeriodicitySelected(selectedPeriodicity: Periodicity) {
        _selectedPeriodicity.value = selectedPeriodicity
    }

    fun onDescriptionChange(description: String) {
        _description.value = description
    }

    fun onAddClicked(context: Context) {
        // TODO: AGREGAR A LA BD
        Toast.makeText(context, "Movimiento registrado exitosamente", Toast.LENGTH_SHORT).show()
    }
}