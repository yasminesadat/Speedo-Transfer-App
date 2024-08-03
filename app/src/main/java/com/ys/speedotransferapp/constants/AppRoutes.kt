package com.ys.speedotransferapp.constants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.constants.AppRoutes.LANDINGS_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.HOME_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.SIGN_IN_ROUTE
import com.ys.speedotransferapp.ui.common.LoadingScreen
import com.ys.speedotransferapp.ui.home.HomeScreen
import com.ys.speedotransferapp.ui.landing.LandingPageScreen
import com.ys.speedotransferapp.ui.navigation.MainScreen
import com.ys.speedotransferapp.ui.signin.SignInScreen
import com.ys.speedotransferapp.ui.signup.ExtraSignUpScreen
import com.ys.speedotransferapp.ui.signup.SignUpScreen
import kotlinx.coroutines.launch

object AppRoutes {
    const val HOME_ROUTE = "home"
    const val TRANSFER_ROUTE = "transfer"
    const val TRANSACTIONS_ROUTE = "transactions"
    const val CARDS_ROUTE = "my cards"
    const val MORE_ROUTE = "more"
    const val FAVOURITES_ROUTE = "favourites"
    const val TRANSACTION_ROUTE = "transaction"
    const val LANDINGS_ROUTE = "landings"
    const val SIGN_UP_ROUTE = "sign up"
    const val EXTRA_SIGN_UP_ROUTE = "extra sign up"
    const val SIGN_IN_ROUTE = "sign in"
}



@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        scope.launch {
            UserPreferences.isLoggedIn(context).collect { loggedIn ->
                isLoggedIn = loggedIn
            }
        }
    }

    when (isLoggedIn) {
        null -> LoadingScreen()
        else -> NavHost(
            navController = navController,
            startDestination = if (isLoggedIn == true) HOME_ROUTE else SIGN_IN_ROUTE
        ) {
            composable(route = LANDINGS_ROUTE) {
                LandingPageScreen(navController)
            }
            composable(route = AppRoutes.SIGN_UP_ROUTE) {
                SignUpScreen(navController)
            }
            composable(route = AppRoutes.EXTRA_SIGN_UP_ROUTE) {
                ExtraSignUpScreen(navController)
            }
            composable(route = AppRoutes.SIGN_IN_ROUTE) {
                SignInScreen(navController, onLoginSuccess = {
                    scope.launch {
                        UserPreferences.setLoggedIn(context, true)
                    }
                    navController.navigate(HOME_ROUTE) {
                        popUpTo(LANDINGS_ROUTE) { inclusive = true }
                    }
                })
            }
            composable(route = HOME_ROUTE) {
                MainScreen(onLogout = {
                    scope.launch {
                        UserPreferences.setLoggedIn(context, false)
                    }
                    navController.navigate(AppRoutes.SIGN_IN_ROUTE) {
                        popUpTo(HOME_ROUTE) { inclusive = true }
                    }
                })
            }
        }
    }
}
