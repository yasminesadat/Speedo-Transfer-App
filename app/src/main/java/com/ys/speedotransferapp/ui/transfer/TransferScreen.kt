package com.ys.speedotransferapp.ui.transfer

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.constants.AppRoutes

import com.ys.speedotransferapp.ui.common.CommonComposableViewModel
import com.ys.speedotransferapp.ui_model.TransferStep
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.common.InputField
import com.ys.speedotransferapp.ui.favourite.FavouriteViewModel
import com.ys.speedotransferapp.ui.home.HomeViewModel
import com.ys.speedotransferapp.ui.theme.CosmicLatte
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.P20
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.appTypography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransferScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = { },
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(CosmicLatte, P20)
                )
            ),
        containerColor = Color.Transparent
    ) {
        val viewModel = remember { TransferScreenViewModel( context.getSharedPreferences("auth_data", Context.MODE_PRIVATE)
            .getString("token", "")!!) }
        val currencyViewModel = remember { CurrenciesViewModel() }
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            viewModel.loadTransferDetails(context)
            currencyViewModel.loadSelectedCurrencyOptionsList(
                context,
                listOf("selected_option_index_1", "selected_option_index_2")
            )
        }
        val state by viewModel.state.collectAsState()
        val amount by viewModel.amount_sending.collectAsState()
        val recName by viewModel.recName.collectAsState()
        val currencies by currencyViewModel.selectedOptionsList.collectAsState()
        var isValid: Boolean = false


        Log.d("TransferScreen", "Transfer details loaded: Amount=$amount, Name=$recName")
        Log.d("TransferScreen", "Currency options loaded: ${currencies.size}")

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Header(text = "Transfer", navController = navController)
            TransferHeader(state.currentStep)
            when (state.currentStep) {
                TransferStep.AMOUNT -> isValid = AmountStep(
                    navController = navController,
                )

                TransferStep.CONFIRMATION -> ConfirmationStep(
                    navController = navController
                )

                TransferStep.PAYMENT -> PaymentStep(
                    navController = navController
                )
            }
            Button(
                onClick = {
                    when (state.currentStep) {
                        TransferStep.AMOUNT -> {
                            if (isValid) {
                                viewModel.goToNextStep()
                            }
                        }

                        TransferStep.CONFIRMATION -> {
                            viewModel.sendNotification(
                                title = "Transfer Successful",
                                text = "You have successfully transferred $amount ${currencies[0].curr_code} to $recName",
                                context = context
                            )
                            viewModel.confirmTransfer()
                        }

                        TransferStep.PAYMENT -> {
                            // Reset transfer and navigate to home
                            viewModel.resetTransfer()
                            viewModel.clearTransferDetails(context)
                            currencyViewModel.clearCurrencyDetails(context)
                            navController.navigate(AppRoutes.HOME_ROUTE)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = P300),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                Text(
                    when (state.currentStep) {
                        TransferStep.AMOUNT -> "Continue"
                        TransferStep.CONFIRMATION -> "Confirm"
                        TransferStep.PAYMENT -> "Back to Home"
                    }
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            if (state.currentStep == TransferStep.CONFIRMATION) {
                OutlinedButton(
                    onClick = viewModel::goToPreviousStep,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = P300 // Use the color that matches your design
                    ),
                    border = BorderStroke(1.dp, P300), // Border color
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                ) {
                    Text(
                        "Previous",
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TransferScreenPreview() {
    val navController = rememberNavController()
    navController.navigate(AppRoutes.HOME_ROUTE)
    TransferScreen(navController = navController)
}