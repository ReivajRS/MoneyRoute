package com.example.moneyroute.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowElement(
    modifier: Modifier = Modifier,
    text: String,
    content: @Composable () -> Unit
) {
    // TODO: GENERALIZAR LOS ESPACIOS
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, modifier = Modifier.width(100.dp))
        Spacer(modifier = Modifier.width(16.dp))
        content()
    }
}