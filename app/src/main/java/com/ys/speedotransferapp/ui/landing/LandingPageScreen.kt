package com.ys.speedotransferapp.ui.landing

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P20
import com.ys.speedotransferapp.ui.theme.appTypography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LandingPageScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {},
        modifier = Modifier
            .fillMaxSize()
            .background(color = P300),
        containerColor = Color.Transparent
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Speedo Transfer", style = appTypography.displayLarge, color = Color.White)
        }
    }
}

@Preview
@Composable
private fun LandingScreenPagePreview() {
    LandingPageScreen()
}