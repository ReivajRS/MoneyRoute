package com.example.moneyroute.movements.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.Periodicity
import com.example.moneyroute.movements.data.RepetitionType
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun MovementDescriptionDialog(
    modifier: Modifier = Modifier,
    movement: Movement,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Row {
                IconButton(
                    onClick = { onDismiss() },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.button_close),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        onDismiss()
                        onDelete()
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.text_delete),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        onDismiss()
                        onEdit()
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(R.string.text_edit),
                    )
                }
            }
        },
        text = {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "${movement.type} - ${movement.category}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movement.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MovementDescriptionDialogPreview() {
    MoneyRouteTheme {
        MovementDescriptionDialog(
            movement = Movement(
                type = "Ingreso",
                category = "Salario",
                amount = 2000.00,
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                date = 1678901200000L, // 2023-03-15 00:00:00 GMT
                periodicity = Periodicity(
                    description = "Mensual",
                    repetitionType = RepetitionType.MONTHLY,
                    interval = 1
                )
            ),
            onDismiss = {},
            onDelete = {},
            onEdit = {}
        )
    }
}