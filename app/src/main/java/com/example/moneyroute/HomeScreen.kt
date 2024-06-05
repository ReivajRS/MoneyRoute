package com.example.moneyroute

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyroute.components.HeaderImage
import com.example.moneyroute.ui.theme.MoneyRouteTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onBack: () -> Unit
) {
    BackHandler(
        onBack = {
            viewModel.logout()
            onBack()
        }
    )
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        HeaderImage(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(240.dp),
            painter = painterResource(id = R.drawable.no_bg_logo)
        )
    }
}