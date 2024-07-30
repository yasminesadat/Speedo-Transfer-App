package com.ys.speedotransferapp.model

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    val route : String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val iconText: String
)