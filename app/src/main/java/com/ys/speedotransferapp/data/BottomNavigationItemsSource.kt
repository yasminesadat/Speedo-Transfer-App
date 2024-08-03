package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui_model.BottomNavigationItem
import com.ys.speedotransferapp.constants.AppRoutes.CARDS_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.HOME_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.MORE_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.TRANSACTIONS_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.TRANSFER_ROUTE

class BottomNavigationItemsSource {
    fun get(): List<BottomNavigationItem> {
        val destinations = mutableListOf<BottomNavigationItem>().apply {
            add(
                BottomNavigationItem(
                    route = HOME_ROUTE,
                    icon = R.drawable.home,
                    label = "Home"
                )
            )
            add(
                BottomNavigationItem(
                    route = TRANSFER_ROUTE,
                    icon = R.drawable.transfer,
                    label = "Transfer"
                )
            )
            add(
                BottomNavigationItem(
                    route = TRANSACTIONS_ROUTE,
                    icon = R.drawable.transaction,
                    label = "Transactions"
                )
            )
            add(
                BottomNavigationItem(
                    route = CARDS_ROUTE,
                    icon = R.drawable.my_cards,
                    label = "My cards"
                )
            )
            add(
                BottomNavigationItem(
                    route = MORE_ROUTE,
                    icon = R.drawable.more,
                    label = "More"
                )
            )
        }

        return destinations
    }
}