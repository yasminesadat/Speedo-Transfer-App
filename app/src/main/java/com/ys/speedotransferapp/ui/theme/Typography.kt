package com.ys.speedotransferapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.ys.speedotransferapp.R

// Define your custom font family
val customFontFamily = FontFamily(
    Font(R.font.inter, FontWeight.Normal),
    Font(R.font.inter, FontWeight.Bold)
)

// Define typography
val appTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    // Define other text styles like bodyLarge, bodyMedium, etc. as needed
)