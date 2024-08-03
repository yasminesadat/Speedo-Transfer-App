package com.ys.speedotransferapp.ui_model

import androidx.annotation.DrawableRes

data class OptionItem(
    @DrawableRes val icon: Int,
    val title: String,
    val isLast: Boolean = false
)