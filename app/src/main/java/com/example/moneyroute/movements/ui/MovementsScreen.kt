package com.example.moneyroute.movements.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyroute.R
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun MovementsScreen(modifier: Modifier = Modifier) {
    // TODO: MOSTRAR LAS COSAS DE LA BASE DE DATOS

    MoneyRouteTheme {
        Scaffold(
            topBar = { MovementsTopBar() }
        ) { innerPadding ->
            MovementsContent(modifier = Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementsTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.title_movements),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun MovementsContent(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.text_amount),
        style = MaterialTheme.typography.titleMedium
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MovementsScreenPreview() {
    MoneyRouteTheme {
        Scaffold(
            topBar = { MovementsTopBar() }
        ) { innerPadding ->
            MovementsContent(modifier = Modifier.padding(innerPadding))
        }
    }
}