package com.example.moneyroute.account.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.authentication.data.User
import com.example.moneyroute.components.HeaderImage
import com.example.moneyroute.utilities.Utilities

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel
) {
    val state by viewModel.state
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AccountContent(userAccount = state.userAccount)
        if (state.error.isNotBlank()) {
            Text(text = stringResource(R.string.error_loading_data), modifier = Modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
            Text(text = state.error, modifier = modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.error)
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun AccountContent(
    modifier: Modifier = Modifier,
    userAccount: User
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderImage(
            painter = painterResource(id = R.drawable.account),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.text_email),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = userAccount.email,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.text_full_name),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${userAccount.firstName} ${userAccount.lastName}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.text_register_date),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = Utilities.getFormattedDate(Utilities.convertDateFromEpochMilliToLocalDate(userAccount.registerDate)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}