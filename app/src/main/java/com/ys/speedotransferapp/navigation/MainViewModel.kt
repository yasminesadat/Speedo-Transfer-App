package com.ys.speedotransferapp.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ys.speedotransferapp.navigation.AppRoutes.CARDS_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.FAVOURITES_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.HOME_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.MORE_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.TRANSACTIONS_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.TRANSFER_ROUTE

class MainViewModel : ViewModel() {


    var selectedItemIndex by  mutableIntStateOf(0)
        private set

    fun setItemIndex(newIndex: Int) {
        selectedItemIndex = newIndex
    }

    fun updateItemIndex(currentRoute: String?) {
        val selectedIndex = when (currentRoute) {
            HOME_ROUTE -> 0
            TRANSFER_ROUTE -> 1
            TRANSACTIONS_ROUTE -> 2
            CARDS_ROUTE -> 3
            MORE_ROUTE, FAVOURITES_ROUTE -> 4
            else -> 0
        }
       selectedItemIndex = selectedIndex
    }
}