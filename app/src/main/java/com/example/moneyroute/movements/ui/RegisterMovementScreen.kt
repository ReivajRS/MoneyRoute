package com.example.moneyroute.movements.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.components.AddButton
import com.example.moneyroute.components.AmountField
import com.example.moneyroute.components.CustomDatePicker
import com.example.moneyroute.components.RowElement
import com.example.moneyroute.movements.data.Periodicity
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import com.example.moneyroute.utilities.FirebaseManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterMovementScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterMovementViewModel,
    firebaseManager: FirebaseManager,
    isPeriodical: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        RegisterMovement(
            isPeriodicMovement = isPeriodical,
            viewModel = viewModel,
            firebaseManager = firebaseManager
        )
    }
}

@Composable
fun RegisterMovement(
    modifier: Modifier = Modifier,
    isPeriodicMovement: Boolean,
    viewModel: RegisterMovementViewModel,
    firebaseManager: FirebaseManager
) {
    val movementState: MovementState by viewModel.movementState.collectAsState()
    val movementTypes: List<String> by viewModel.movementTypes.collectAsState()
    val categories: List<String> by viewModel.categories.collectAsState()
    val periodicities: List<Periodicity> by viewModel.periodicities.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        RowElement(
            text = stringResource(id = R.string.text_amount),
        ) {
            AmountField(
                amount = movementState.amount,
                onAmountChange = { viewModel.onAmountChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(
            text = stringResource(id = R.string.text_type),
        ) {
            MovementTypeDropDownMenu(
                movementTypes = movementTypes,
                selectedMovementType = movementState.movementType,
                onMovementTypeSelected = { viewModel.onMovementTypeSelected(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(
            text = stringResource(id = R.string.text_category),
        ) {
            CategoryDropdownMenu(
                categories = categories,
                selectedCategory = movementState.category,
                onCategorySelected = { viewModel.onCategorySelected(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(
            text = stringResource(id = R.string.text_date),
        ) {
            CustomDatePicker(
                selectedDate = movementState.date,
                onDateSelected = { viewModel.onDateSelected(it) }
            )
        }

        if (isPeriodicMovement) {
            Spacer(modifier = Modifier.height(16.dp))

            RowElement(
                text = stringResource(id = R.string.text_periodicity),
            ) {
                PeriodicityDropDownMenu(
                    periodicities = periodicities,
                    selectedPeriodicity = movementState.periodicity,
                    onPeriodicitySelected = { viewModel.onPeriodicitySelected(it) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = stringResource(id = R.string.text_description))
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionField(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            description = movementState.description,
            onDescriptionChange = { viewModel.onDescriptionChange(it) }
        )

        Spacer(modifier = Modifier.height(40.dp))

        AddButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(160.dp),
            onAddClicked = { viewModel.onAddClicked(context, firebaseManager, coroutineScope) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementTypeDropDownMenu(
    modifier: Modifier = Modifier,
    movementTypes: List<String>,
    selectedMovementType: String,
    onMovementTypeSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedMovementType,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            placeholder = {
                Text(text = stringResource(id = R.string.text_select))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // TODO: AGREGAR FUNCIONAMIENTO CON TIPOS DE MOVIMIENTOS RECUPERADOS DE LA BD
            movementTypes.forEach { movementType ->
                DropdownMenuItem(
                    text = { Text(text = movementType) },
                    onClick = {
                        onMovementTypeSelected(movementType)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdownMenu(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedCategory,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            placeholder = {
                Text(text = stringResource(id = R.string.text_select))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // TODO: AGREGAR FUNCIONAMIENTO CON CATEGORIAS RECUPERADAS DE LA BD
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodicityDropDownMenu(
    modifier: Modifier = Modifier,
    periodicities: List<Periodicity>,
    selectedPeriodicity: Periodicity?,
    onPeriodicitySelected: (Periodicity) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedPeriodicity?.description ?: "",
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            placeholder = {
                Text(text = stringResource(id = R.string.text_select))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // TODO: AGREGAR FUNCIONAMIENTO CON PERIODICIDADES RECUPERADAS DE LA BD
            periodicities.forEach { periodicity ->
                DropdownMenuItem(
                    text = { Text(text = periodicity.description) },
                    onClick = {
                        onPeriodicitySelected(periodicity)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DescriptionField(
    modifier: Modifier = Modifier,
    description: String,
    onDescriptionChange: (String) -> Unit){
    TextField(
        value = description,
        onValueChange = { onDescriptionChange(it) },
        modifier = modifier
    )
}
//
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun RegisterMovementScreenPreview() {
//    val isPeriodicMovement = true
//    MoneyRouteTheme {
//        RegisterMovementScreen(
//            viewModel = RegisterMovementViewModel(),
//            isPeriodical = isPeriodicMovement
//        )
//    }
//}
