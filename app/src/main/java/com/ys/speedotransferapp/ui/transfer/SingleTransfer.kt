package com.ys.speedotransferapp.ui.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.model.TransferStep
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.appTypography

@Composable
fun TransferHeader(currentStep: TransferStep) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SingleTransferHeader(
            stepNumber = "01",
            stepName = "Amount",
            currentColor = if (currentStep == TransferStep.AMOUNT) P300 else Color.Gray
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .background(color = if (currentStep == TransferStep.CONFIRMATION || currentStep == TransferStep.PAYMENT) P300 else Color.Gray)
        )
        SingleTransferHeader(
            stepNumber = "02",
            stepName = "Confirmation",
            currentColor = if (currentStep == TransferStep.CONFIRMATION) P300 else Color.Gray
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .background(color = if (currentStep == TransferStep.PAYMENT) P300 else Color.Gray)
        )
        SingleTransferHeader(
            stepNumber = "03",
            stepName = "Payment",
            currentColor = if (currentStep == TransferStep.PAYMENT) P300 else Color.Gray
        )
    }
}

@Composable
fun SingleTransferHeader(stepNumber: String, stepName: String, currentColor:Color, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircleWithNumber(number = stepNumber, currentColor, modifier = modifier)
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = stepName)
    }


}

@Composable
fun CircleWithNumber(number: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(40.dp)
            .background(color = Color.White, shape = CircleShape)
            .border(width = 2.dp, color = color, shape = CircleShape)

    ) {
        Text(text = number, color = color, fontSize = 20.sp)
    }
}

@Composable
fun AmountStep(onAmountEntered: (Double) -> Unit) {
    var amountText by remember { mutableStateOf("") }

    Column {
        Text(text = "How much are you sending?", style = appTypography.titleLarge)
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Choose Currency", style = appTypography.bodyMedium)
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()  // Fill the width instead of size to avoid clipping issues
                .height(100.dp)  // Set a fixed height or adjust as needed
                .clip(RoundedCornerShape(8.dp))  // Adjust the radius as needed
                .background(G0)  // Use your desired background color
        ) {
            Column(
                modifier = Modifier.padding(16.dp)  // Adjust padding inside the box as needed
            ) {
                Text(text = "1 USD = 0.74 GBP", style = appTypography.bodyMedium)
            }
        }
    }
}

@Composable
fun ConfirmationStep(
    amount: Double,
    onConfirm: () -> Unit,
    onPrevious: () -> Unit
) {
    Column {
        Text("Confirm transfer of $$amount")
        Button(onClick = onConfirm) {
            Text("Confirm")
        }
        Button(onClick = onPrevious) {
            Text("Previous")
        }
    }
}

@Composable
fun PaymentStep(
    amount: Double,
    isSuccessful: Boolean,
    onBackToHome: () -> Unit
) {
    Column {
        if (isSuccessful) {
            Text("Transfer of $$amount successful!")
        } else {
            Text("Processing transfer...")
        }
        Button(onClick = onBackToHome) {
            Text("Back to Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleTransferHeaderPreview() {
    TransferHeader(currentStep = TransferStep.AMOUNT)
}