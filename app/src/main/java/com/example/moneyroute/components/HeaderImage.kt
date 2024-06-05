package com.example.moneyroute.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.moneyroute.R

@Composable
fun HeaderImage(modifier: Modifier = Modifier, painter: Painter) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}