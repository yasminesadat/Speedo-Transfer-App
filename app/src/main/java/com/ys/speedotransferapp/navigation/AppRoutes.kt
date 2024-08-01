package com.ys.speedotransferapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

object AppRoutes {
    const val HOME_ROUTE = "home"
    const val TRANSFER_ROUTE = "transfer"
    const val TRANSACTIONS_ROUTE = "transactions"
    const val CARDS_ROUTE = "my cards"
    const val MORE_ROUTE = "more"
    const val FAVOURITES_ROUTE = "favourites"
    const val SIGN_UP = "sign up"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

}

