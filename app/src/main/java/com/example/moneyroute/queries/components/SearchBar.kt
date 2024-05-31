package com.example.moneyroute.queries.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onSearchBarChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onSearchBarChange(it) },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        label = {
            Text(text = "Buscar")
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}