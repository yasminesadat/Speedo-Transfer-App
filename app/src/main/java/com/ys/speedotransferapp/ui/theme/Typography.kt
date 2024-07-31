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
    Font(R.font.inter, FontWeight.Normal),
    Font(R.font.inter, FontWeight.Bold)
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
        fontSize = 30.sp
    ),
    //header for page
    headlineMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        color = Black
    ),
    // gray options
    titleMedium = TextStyle(
        fontFamily = additionalFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = DarkGrey
    )
)