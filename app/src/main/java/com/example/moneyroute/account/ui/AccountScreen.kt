package com.example.moneyroute.account.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.goals.ui.register.RegisterGoal
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AccountContent(viewModel = viewModel)
    }
}

@Composable
fun AccountContent(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.placeholder_email_field),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge
        )
        
    }
}

@Preview
@Composable
fun AccountScreenPreview() {
    MoneyRouteTheme {
        AccountScreen(viewModel = AccountViewModel())
    }
}