//package com.ys.speedotransferapp.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.ys.speedotransferapp.navigation.AppRoutes.HOME_ROUTE
//import com.ys.speedotransferapp.navigation.AppRoutes.MORE_ROUTE
//import com.ys.speedotransferapp.ui.home.HomeScreen
//import com.ys.speedotransferapp.ui.more.MoreScreen
//
//object AppRoutes {
//    const val HOME_ROUTE = "home"
//    const val TRANSFER_ROUTE = "transfer"
//    const val TRANSACTIONS_ROUTE = "transactions"
//    const val CARDS_ROUTE = "my cards"
//    const val MORE_ROUTE = "more"
//}
//
//@Composable
//fun AppNavHost(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = HOME_ROUTE, modifier = modifier) {
//        composable(route = HOME_ROUTE) { HomeScreen(navController) }
//        composable(MORE_ROUTE) { MoreScreen(navController)}
//    }
//
//}