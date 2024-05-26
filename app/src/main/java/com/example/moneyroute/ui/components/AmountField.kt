package com.example.moneyroute.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AmountField(
    modifier: Modifier = Modifier,
    amount: String,
    onAmountChange: (String) -> Unit
) {
    TextField(
        value = amount,
        onValueChange = { onAmountChange(it) },
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