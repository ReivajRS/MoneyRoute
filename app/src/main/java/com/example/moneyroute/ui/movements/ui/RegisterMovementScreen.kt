package com.example.moneyroute.ui.movements.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.R
import com.example.moneyroute.ui.theme.MoneyRouteTheme
import com.example.moneyroute.ui.theme.Typography

@Composable
fun RegisterMovementScreen(modifier: Modifier, viewModel: RegisterViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        RegisterMovement(
            modifier = Modifier.align(Alignment.Center),
            viewModel = viewModel
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterMovement(modifier: Modifier = Modifier, viewModel: RegisterViewModel) {
    Scaffold(
        topBar = { RegisterMovementTopBar() }
    ) {

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterMovementTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.title_registrar_movimiento),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterMovementScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        RegisterMovement(
            modifier = Modifier.align(Alignment.Center),
            viewModel = RegisterViewModel()
        )
    }
}