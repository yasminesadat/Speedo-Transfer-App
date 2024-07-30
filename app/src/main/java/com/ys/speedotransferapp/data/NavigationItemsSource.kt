package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.model.BottomNavigationItem
import com.ys.speedotransferapp.navigation.AppRoutes.CARDS_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.HOME_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.MORE_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.TRANSACTIONS_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.TRANSFER_ROUTE

class NavigationItemsSource {
    fun get(): List<BottomNavigationItem> {
        val destinations = mutableListOf<BottomNavigationItem>().apply {
            add(
                BottomNavigationItem(
                    route = HOME_ROUTE,
                    selectedIcon = R.drawable.home_selected,
                    unselectedIcon = R.drawable.home,
                    iconText = "Home"
                )
            )
            add(
                BottomNavigationItem(
                    route = TRANSFER_ROUTE,
                    selectedIcon = R.drawable.transfer_selected,
                    unselectedIcon = R.drawable.transfer,
                    iconText = "Transfer"
                )
            )
            add(
                BottomNavigationItem(
                    route = TRANSACTIONS_ROUTE,
                    selectedIcon = R.drawable.transactions_selected,
                    unselectedIcon = R.drawable.transactions,
                    iconText = "Transactions"
                )
            )
            add(
                BottomNavigationItem(
                    route = CARDS_ROUTE,
                    selectedIcon = R.drawable.mycards_selected,
                    unselectedIcon = R.drawable.mycards,
                    iconText = "My cards"
                )
            )
            add(
                BottomNavigationItem(
                    route = MORE_ROUTE,
                    selectedIcon = R.drawable.more_selected,
                    unselectedIcon = R.drawable.more,
                    iconText = "More"
                )
            )

        }

        return destinations
    }
}