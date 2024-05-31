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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun FloatingActionButtonWithMenu(
    modifier: Modifier = Modifier,
    onRegisterMovementClick: () -> Unit,
    onRegisterPeriodicMovementClick: () -> Unit
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
                Text(
                    text = stringResource(id = R.string.title_register_movement),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded = false
                            onRegisterMovementClick()
                        }
                        .padding(8.dp)
                )
                HorizontalDivider()
                Text(
                    text = stringResource(id = R.string.title_register_periodic_movement),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded = false
                            onRegisterPeriodicMovementClick()
                        }
                        .padding(8.dp)
                )
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
//    }
}

@Preview(showBackground = true)
@Composable
private fun FloatingActionButtonWithMenuPreview() {
    MoneyRouteTheme {
        FloatingActionButtonWithMenu(
            onRegisterMovementClick = { /* TODO: CAMBIAR DE PANTALLA */ },
            onRegisterPeriodicMovementClick = { /* TODO: CAMBIAR DE PANTALLA */ }
        )
    }
}