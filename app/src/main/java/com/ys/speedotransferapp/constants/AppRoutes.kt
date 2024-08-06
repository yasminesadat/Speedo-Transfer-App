package com.ys.speedotransferapp.constants

import com.ys.speedotransferapp.database.UserPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.constants.AppRoutes.AMOUNT_STEP_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.CONFIRMATION_STEP_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.EXTRA_SIGN_UP_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.HOME_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.LANDINGS_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.PAYMENT_STEP_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.SIGN_IN_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.SIGN_UP_ROUTE
import com.ys.speedotransferapp.ui.common.LoadingScreen
import com.ys.speedotransferapp.ui.landing.LandingPageScreen
import com.ys.speedotransferapp.ui.navigation.MainScreen
import com.ys.speedotransferapp.ui.signin.SignInScreen
import com.ys.speedotransferapp.ui.signup.ExtraSignUpScreen
import com.ys.speedotransferapp.ui.signup.SignUpScreen
//import com.ys.speedotransferapp.ui.transfer.AmountStep
//import com.ys.speedotransferapp.ui.transfer.ConfirmationStep
//import com.ys.speedotransferapp.ui.transfer.PaymentStep
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
    const val AMOUNT_STEP_ROUTE = "amount step"
    const val CONFIRMATION_STEP_ROUTE = "confirmation step"
    const val PAYMENT_STEP_ROUTE = "payment step"
}



@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        scope.launch {
            UserPreferences.isLoggedIn(context).collect { loggedIn: Boolean ->
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
            composable(route = SIGN_UP_ROUTE) {
                SignUpScreen(navController)
            }
            composable(route = EXTRA_SIGN_UP_ROUTE) {
                ExtraSignUpScreen(navController)
            }
            composable(route = SIGN_IN_ROUTE) {
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
                    navController.navigate(SIGN_IN_ROUTE) {
                        popUpTo(HOME_ROUTE) { inclusive = true }
                    }
                })
            }

        }
    }
}
