package com.ys.speedotransferapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.navigation.AppNavHost
import com.ys.speedotransferapp.navigation.MainScreen
import com.ys.speedotransferapp.ui.signup.SignUpScreen
import com.ys.speedotransferapp.ui.signup.SignUpViewModel
import com.ys.speedotransferapp.ui.theme.SpeedoTransferAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoTransferAppTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}
