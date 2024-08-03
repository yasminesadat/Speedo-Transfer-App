package com.ys.speedotransferapp.ui.landing

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.constants.AppRoutes
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.appTypography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LandingPageScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {},
        modifier = Modifier
            .fillMaxSize()
            .background(color = P300)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount > 0) {
                        navController.navigate(AppRoutes.SIGN_IN_ROUTE)
                    }
                }
            }.clickable { navController.navigate(AppRoutes.SIGN_IN_ROUTE) },
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
    // Provide a dummy NavController for preview
    LandingPageScreen(navController = rememberNavController())
}