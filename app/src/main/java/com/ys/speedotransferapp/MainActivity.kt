package com.ys.speedotransferapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ys.speedotransferapp.constants.AppNavHost
import com.ys.speedotransferapp.ui.navigation.MainScreen
import com.ys.speedotransferapp.ui.signup.ExtraSignUpScreen
import com.ys.speedotransferapp.ui.signup.SignUpViewModel
import com.ys.speedotransferapp.ui.theme.SpeedoTransferAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoTransferAppTheme {
                    AppNavHost()
            }
        }
    }
}

