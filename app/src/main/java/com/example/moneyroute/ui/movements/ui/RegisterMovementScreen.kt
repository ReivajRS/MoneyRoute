package com.example.moneyroute.ui.movements.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.ui.login.domain.Periodicity
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import java.time.Instant
import java.time.ZoneId

@Composable
fun RegisterMovementScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterMovementViewModel,
    isPeriodicMovement: Boolean
) {
    MoneyRouteTheme {
        Scaffold(
            topBar = { RegisterMovementTopBar(isPeriodicMovement = isPeriodicMovement) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                RegisterMovement(
                    isPeriodicMovement = isPeriodicMovement,
                    viewModel = viewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterMovementTopBar(
    modifier: Modifier = Modifier,
    isPeriodicMovement: Boolean
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(
                        id = if (isPeriodicMovement) R.string.title_register_periodic_movement
                        else R.string.title_register_movement
                    ),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun RegisterMovement(
    modifier: Modifier = Modifier,
    isPeriodicMovement: Boolean,
    viewModel: RegisterMovementViewModel
) {
    val amount: String by viewModel.amount.collectAsState()
    val movementTypes: List<String> by viewModel.movementTypes.collectAsState()
    val selectedMovementType: String by viewModel.selectedMovementType.collectAsState()
    val categories: List<String> by viewModel.categories.collectAsState()
    val selectedCategory: String by viewModel.selectedCategory.collectAsState()
    val selectedDate: Long? by viewModel.selectedDate.collectAsState()
    val periodicities: List<Periodicity> by viewModel.periodicities.collectAsState()
    val selectedPeriodicity: Periodicity? by viewModel.selectedPeriodicity.collectAsState()
    val description: String by viewModel.description.collectAsState()
    val context = LocalContext.current

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
                amount = amount,
                onTextFieldChange = { viewModel.onAmountChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(
            text = stringResource(id = R.string.text_type),
        ) {
            MovementTypeDropDownMenu(
                movementTypes = movementTypes,
                selectedMovementType = selectedMovementType,
                onMovementTypeSelected = { viewModel.onMovementTypeSelected(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(
            text = stringResource(id = R.string.text_category),
        ) {
            CategoryDropdownMenu(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { viewModel.onCategorySelected(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RowElement(
            text = stringResource(id = R.string.text_date),
        ) {
            MovementDatePicker(
                selectedDate = selectedDate,
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
                    selectedPeriodicity = selectedPeriodicity,
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
            description = description,
            onTextFieldChange = { viewModel.onDescriptionChange(it) }
        )

        Spacer(modifier = Modifier.height(40.dp))

        AddMovementButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(160.dp),
            onAddClicked = { viewModel.onAddClicked(context) }
        )
    }
}

@Composable
fun RowElement(
    modifier: Modifier = Modifier,
    text: String,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, modifier = Modifier.width(100.dp))
        Spacer(modifier = Modifier.width(16.dp))
        content()
    }
}

@Composable
fun AmountField(
    modifier: Modifier = Modifier,
    amount: String,
    onTextFieldChange: (String) -> Unit
) {
    TextField(
        value = amount,
        onValueChange = { onTextFieldChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        placeholder = {
            Text(text = "0.0")
        },
        prefix = {
            Text(text = "$")
        },
        modifier = modifier.fillMaxWidth()
    )
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
fun MovementDatePicker(
    modifier: Modifier = Modifier,
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var dateString by remember { mutableStateOf("") }
    if (selectedDate != null) {
        val localDate = Instant.ofEpochMilli(selectedDate).atZone(ZoneId.of("UTC")).toLocalDate()
        dateString = "${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}"
    }

    OutlinedTextField(
        value = dateString,
        onValueChange = {  },
        singleLine = true,
        readOnly = true,
        placeholder = {
            Text(text = stringResource(id = R.string.text_select))
        },
        trailingIcon = {
            IconButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Pick a selectedDate")
            }
        },
        modifier = modifier.fillMaxWidth()
    )

    if (showDialog) {
        val dateState = rememberDatePickerState(initialSelectedDateMillis = selectedDate)
        DatePickerDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                Button(onClick = {
                    onDateSelected(dateState.selectedDateMillis)
                    showDialog = false
                }) {
                    Text(text = stringResource(id = R.string.text_confirm))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.text_cancel))
                }
            }
        ) {
            DatePicker(
                state = dateState
            )
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
    onTextFieldChange: (String) -> Unit){
    TextField(
        value = description,
        onValueChange = { onTextFieldChange(it) },
        modifier = modifier
    )
}

@Composable
fun AddMovementButton(modifier: Modifier = Modifier, onAddClicked: () -> Unit) {
    Button(onClick = { onAddClicked() }, modifier = modifier) {
        Text(text = stringResource(id = R.string.text_add))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterMovementScreenPreview() {
    val isPeriodicMovement = true
    MoneyRouteTheme {
        Scaffold(
            topBar = { RegisterMovementTopBar(isPeriodicMovement = isPeriodicMovement) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                RegisterMovement(
                    modifier = Modifier.align(Alignment.Center),
                    isPeriodicMovement = isPeriodicMovement,
                    viewModel = RegisterMovementViewModel()
                )
            }
        }
    }
}
