package com.example.moneyroute.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.moneyroute.R
import com.example.moneyroute.utilities.Utilities

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.text_select),
    enable: Boolean = true,
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var dateString by remember { mutableStateOf("") }

    if (selectedDate != null) {
        val localDate = Utilities.convertDateFromEpochMilliToLocalDate(selectedDate)
        dateString = Utilities.getFormattedDate(localDate)
    } else {
        dateString = ""
    }

    OutlinedTextField(
        value = dateString,
        onValueChange = {  },
        singleLine = true,
        readOnly = true,
        placeholder = {
            Text(text = placeholder)
        },
        trailingIcon = {
            IconButton(onClick = { showDialog = true }, enabled = enable) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Select a date")
            }
        },
        enabled = enable,
        modifier = modifier
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