package com.example.moneyroute.goals.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.goals.data.Goal

@Composable
fun GoalDescriptionDialog(
    modifier: Modifier = Modifier,
    goal: Goal,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.button_close))
            }
        },
        text = {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = goal.label,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = goal.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun GoalDescriptionDialogPreview() {
    GoalDescriptionDialog(
        goal = Goal(
            label = "Meta 1: Skibidi dob dob dob dob yes yes yes yes skibidi dib dib dib dib",
            currentAmount = 450.0,
            goalAmount = 1000.0,
            startDate = 1717039194000,
            goalDate = 1717539194000,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            status = "Activa"
        ),
        onDismiss = {}
    )
}