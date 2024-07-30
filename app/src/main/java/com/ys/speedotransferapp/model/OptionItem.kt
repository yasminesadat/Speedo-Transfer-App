package com.ys.speedotransferapp.model

import androidx.annotation.DrawableRes

data class OptionItem(
    @DrawableRes val icon: Int,
    val title: String,
    val isLast: Boolean = false
)