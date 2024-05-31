package com.example.moneyroute.goals.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.goals.data.Goal
import com.example.moneyroute.utilities.Utilities
import com.example.moneyroute.utilities.Utilities.convertDateFromEpochMilliToLocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalCard(
    modifier: Modifier = Modifier,
    goal: Goal,
    onCardClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.padding(16.dp),
        onClick = {
            onCardClick()
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = goal.label,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(8.dp))

            StatusRow(status = goal.status)

            Spacer(modifier = Modifier.height(8.dp))

            AmountsRow(currentAmount = goal.currentAmount, goalAmount = goal.goalAmount)

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = (goal.currentAmount / goal.goalAmount).toFloat(),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            DatesRow(startDate = goal.startDate, goalDate = goal.goalDate)
        }
    }
}

@Composable
fun StatusRow(
    modifier: Modifier = Modifier,
    status: String
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "${stringResource(id = R.string.text_status)}:",
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = status,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
        )
    }
}

@Composable
fun AmountsRow(
    modifier: Modifier = Modifier,
    currentAmount: Double,
    goalAmount: Double
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.text_current),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = currentAmount.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column {
            Text(
                text = stringResource(id = R.string.text_goal),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = goalAmount.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DatesRow(
    modifier: Modifier = Modifier,
    startDate: Long,
    goalDate: Long
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.text_start_date),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = Utilities.getFormattedDate(convertDateFromEpochMilliToLocalDate(startDate)),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Column {
            Text(
                text = stringResource(id = R.string.text_goal_date),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = Utilities.getFormattedDate(convertDateFromEpochMilliToLocalDate(goalDate)),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GoalCardPreview() {
    GoalCard(
        modifier = Modifier,
        goal = Goal(
            label = "Meta 1",
            currentAmount = 450.0,
            goalAmount = 1000.0,
            startDate = 1717039194000,
            goalDate = 1717539194000,
            description = "Descripcion de la meta numero 1",
            status = "Activa"
        ),
        onCardClick = {  }
    )
}