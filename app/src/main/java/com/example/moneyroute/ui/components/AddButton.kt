package com.example.moneyroute.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.moneyroute.R

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit
) {
    Button(
        onClick = { onAddClicked() },
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.text_add))
    }
}