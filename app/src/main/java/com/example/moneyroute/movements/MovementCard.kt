package com.example.moneyroute.movements

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
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.movements.data.Periodicity
import com.example.moneyroute.movements.data.RepetitionType
import com.example.moneyroute.utilities.Utilities
import com.example.moneyroute.utilities.Utilities.convertDateFromEpochMilliToLocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementCard(
    modifier: Modifier = Modifier,
    movement: Movement,
    onCardClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.padding(16.dp),
        onClick = onCardClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movement.type,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextRow(
                title = stringResource(id = R.string.text_category),
                text = movement.category
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextRow(
                title = stringResource(id = R.string.text_amount),
                text = movement.amount.toString()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextRow(
                title = stringResource(id = R.string.text_date),
                text = Utilities.getFormattedDate(convertDateFromEpochMilliToLocalDate(movement.date))
            )

            if (movement.periodicity != null) {
                Spacer(modifier = Modifier.height(8.dp))
                TextRow(
                    title = stringResource(id = R.string.text_periodicity),
                    text = movement.periodicity.description
                )
            }
        }
    }
}

@Composable
fun TextRow(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "${title}:",
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(108.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GoalCardPreview() {
    MovementCard(
        modifier = Modifier,
        movement = Movement(
            type = "Egreso",
            category = "Ropa",
            amount = 150.0,
            description = "Descripcion del movimiento 1",
            date = 999999900000,
            periodicity = Periodicity(description = "Cada dos semanas", RepetitionType.WEEKLY, 2)
        ),
        onCardClick = {  }
    )
}