package com.ys.speedotransferapp.ui.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.model.TransferStep
import com.ys.speedotransferapp.ui.theme.P300

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
            currentColor = P300
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .offset(y = (-16).dp) // Adjust the offset value as needed
                .background(color = P300)
        )
        SingleTransferHeader(
            stepNumber = "02",
            stepName = "Confirmation",
            currentColor = if (currentStep.ordinal >= TransferStep.CONFIRMATION.ordinal) P300 else Color.Gray
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .offset(y = (-16).dp) // Adjust the offset value as needed
                .background(color = if (currentStep.ordinal >= TransferStep.PAYMENT.ordinal) P300 else Color.Gray)
        )
        SingleTransferHeader(
            stepNumber = "03",
            stepName = "Payment",
            currentColor = if (currentStep.ordinal >= TransferStep.PAYMENT.ordinal) P300 else Color.Gray
        )
    }
}



@Composable
fun SingleTransferHeader(
    stepNumber: String,
    stepName: String,
    currentColor: Color,
    modifier: Modifier = Modifier
) {
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














@Preview(showBackground = true)
@Composable
private fun SingleTransferHeaderPreview() {
    TransferHeader(currentStep = TransferStep.AMOUNT)
}