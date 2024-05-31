package com.example.moneyroute.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun FloatingActionButtonWithMenu(
    modifier: Modifier = Modifier,
    fabOptions: List<FabOption>
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .background(Color.White, shape = MaterialTheme.shapes.medium)
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                fabOptions.forEachIndexed { index, it ->
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                expanded = false
                                it.onClick()
                            }
                            .padding(8.dp)
                    )
                    if (index % 2 == 0) {
                        HorizontalDivider()
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FloatingActionButtonWithMenuPreview() {
    MoneyRouteTheme {
        FloatingActionButtonWithMenu(
            fabOptions = listOf(
                FabOption(
                    title = "Registrar movimiento",
                    onClick = {  }
                ),
                FabOption(
                    title = "Registrar periodico",
                    onClick = {  }
                )
            )
        )
    }
}

data class FabOption(
    val title: String,
    val onClick: () -> Unit
)