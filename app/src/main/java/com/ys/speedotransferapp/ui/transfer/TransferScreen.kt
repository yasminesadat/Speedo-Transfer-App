package com.ys.speedotransferapp.ui.transfer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.model.TransferStep
import com.ys.speedotransferapp.navigation.AppRoutes
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.CosmicLatte
import com.ys.speedotransferapp.ui.theme.P20
import com.ys.speedotransferapp.ui.theme.P300

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransferScreen(
    navController: NavController,
    viewModel: TransferScreenViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { Header(text = "Transfer", navController = navController) },
        modifier = modifier.fillMaxSize().background(
            brush = Brush.linearGradient(
                colors = listOf(CosmicLatte, P20)
            )
        ),
        containerColor = Color.Transparent
    ) {
        val state by viewModel.state.collectAsState()

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TransferHeader(state.currentStep)
            when (state.currentStep) {
                TransferStep.AMOUNT -> AmountStep(
                    onAmountEntered = viewModel::setAmount
                )

                TransferStep.CONFIRMATION -> ConfirmationStep(
                    amount = state.amount,
                    onConfirm = viewModel::confirmTransfer,
                    onPrevious = viewModel::goToPreviousStep
                )

                TransferStep.PAYMENT -> PaymentStep(
                    amount = state.amount,
                    isSuccessful = state.isTransferSuccessful,
                    onBackToHome = viewModel::resetTransfer
                )
            }
            Button(
                onClick = {
                    when (state.currentStep) {
                        TransferStep.AMOUNT -> viewModel.goToNextStep()
                        TransferStep.CONFIRMATION -> viewModel.confirmTransfer()
                        TransferStep.PAYMENT -> {
                            viewModel.resetTransfer()
                            navController.navigate(AppRoutes.HOME_ROUTE)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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

            if (state.currentStep != TransferStep.AMOUNT) {
                Button(
                    onClick = viewModel::goToPreviousStep,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = P300),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    Text("Previous")
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
    TransferScreen(navController = navController, viewModel = TransferScreenViewModel())
}