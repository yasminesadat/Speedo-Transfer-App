package com.ys.speedotransferapp.constants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.navigation.AppRoutes.LANDINGS_ROUTE
import com.ys.speedotransferapp.ui.home.HomeScreen
import com.ys.speedotransferapp.ui.landing.LandingPageScreen
import com.ys.speedotransferapp.ui.signin.SignInScreen
import com.ys.speedotransferapp.ui.signup.ExtraSignUpScreen
import com.ys.speedotransferapp.ui.signup.SignUpScreen

object AppRoutes {
    const val HOME_ROUTE = "home"
    const val TRANSFER_ROUTE = "transfer"
    const val TRANSACTIONS_ROUTE = "transactions"
    const val CARDS_ROUTE = "my cards"
    const val MORE_ROUTE = "more"
    const val FAVOURITES_ROUTE = "favourites"
    const val LANDINGS_ROUTE = "landings"
    const val SIGN_UP_ROUTE = "sign up"
    const val EXTRA_SIGN_UP_ROUTE = "extra sign up"
    const val SIGN_IN_ROUTE = "sign in"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LANDINGS_ROUTE){
        composable(route = LANDINGS_ROUTE){
            LandingPageScreen(navController)
        }
        composable(route = AppRoutes.SIGN_UP_ROUTE){
            SignUpScreen(navController)
        }
        composable(route = AppRoutes.EXTRA_SIGN_UP_ROUTE){
            ExtraSignUpScreen(navController)
        }
        composable(route = AppRoutes.SIGN_IN_ROUTE){
            SignInScreen(navController)
        }

        composable(route = AppRoutes.HOME_ROUTE){
            MainScreen(navController)
        }
    }
}

