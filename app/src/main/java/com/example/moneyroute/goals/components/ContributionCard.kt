package com.example.moneyroute.goals.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import com.example.moneyroute.goals.data.Contribution
import com.example.moneyroute.movements.components.TextRow
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import com.example.moneyroute.utilities.Utilities

@Composable
fun ContributionCard(
    modifier: Modifier = Modifier,
    contribution: Contribution,
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
                text = contribution.goalLabel,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextRow(
                title = stringResource(id = R.string.text_amount),
                text = "$${contribution.amount}"
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextRow(
                title = stringResource(id = R.string.text_date),
                text = Utilities.getFormattedDate(Utilities.convertDateFromEpochMilliToLocalDate(contribution.date))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContributionCard() {
    MoneyRouteTheme {
        ContributionCard(contribution = Contribution(goalLabel = "Meta"), onCardClick = {})
    }
}