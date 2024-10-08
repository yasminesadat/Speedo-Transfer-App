package com.ys.speedotransferapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.R

// Define your custom font family
val customFontFamily = FontFamily(
    Font(R.font.inter_semibold, FontWeight.Normal),
    Font(R.font.inter_semibold, FontWeight.Bold)
)
val additionalFontFamily =  FontFamily(
    Font(R.font.poppins_medium, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Bold)
)

// Define typography
val appTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = G900
    ),
    //header for page
    headlineMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp,
        color = G900
    ),
    // gray options
    titleMedium = TextStyle(
        fontFamily = additionalFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = G200
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.inter_medium, FontWeight.Normal)
        ),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = G700
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.inter_regular, FontWeight.Normal)
        ),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = G700
    ),
)