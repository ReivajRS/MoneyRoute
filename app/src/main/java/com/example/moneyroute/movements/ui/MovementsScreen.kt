package com.example.moneyroute.movements.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.movements.MovementList
import com.example.moneyroute.movements.data.Movement
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun MovementsScreen(
    modifier: Modifier = Modifier
) {
    MovementsContent(modifier = modifier)
}

@Composable
fun MovementsContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.text_latest_movements),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        // TODO: QUITAR
        MovementList(movementList = listOf(
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),
            Movement("algo", "algo", 100.0, "algo descriptivo", 10000000000, null),)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MovementsScreenPreview() {
    MoneyRouteTheme {
        MovementsScreen(

        )
    }
}