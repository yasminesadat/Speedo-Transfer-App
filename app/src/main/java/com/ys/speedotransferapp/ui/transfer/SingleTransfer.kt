package com.ys.speedotransferapp.ui.transfer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.model.FavouriteItem
import com.ys.speedotransferapp.model.TransferStep
import com.ys.speedotransferapp.ui.common.CommonComposableViewModel
import com.ys.speedotransferapp.ui.common.CustomOutlinedTextField
import com.ys.speedotransferapp.ui.common.InputField
import com.ys.speedotransferapp.ui.favourite.FavouriteViewModel
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.G100
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
            currentColor = if (currentStep.ordinal >= TransferStep.AMOUNT.ordinal) P300 else Color.Gray
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(2.dp)
                .offset(y = (-16).dp) // Adjust the offset value as needed
                .background(color = if (currentStep.ordinal >= TransferStep.CONFIRMATION.ordinal) P300 else Color.Gray)
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











@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    favourite: FavouriteItem,
    onDismiss: () -> Unit,
    onSave: (FavouriteItem, FavouriteItem) -> Unit,
    viewModel: FavouriteViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        windowInsets = WindowInsets.systemBars
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.favorite),
                    contentDescription = "edit icon",
                    tint = P300,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Favourite List",
                    fontSize = 24.sp
                )

            }
            val name by viewModel.name.collectAsState()
            val accountNumber by viewModel.accountNumber.collectAsState()

            CustomOutlinedTextField(
                header = "Recipient Name",
                value = name,
                onValueChange = { viewModel.setName(it) },
                label = "Enter Cardholder Name",
            )

            CustomOutlinedTextField(
                header = "Recipient Account",
                value = accountNumber,
                onValueChange = {viewModel.setAccountNumber(it)},
                label = "Enter Cardholder Account Number",
                keyboardType = KeyboardType.Number
            )
            Button(
                onClick = {
                    onSave(favourite, FavouriteItem(name, accountNumber))
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = P300),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = name.isNotBlank() && accountNumber.isNotBlank()
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    style = TextStyle(
                        color = Color.White,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun SingleTransferHeaderPreview() {
    TransferHeader(currentStep = TransferStep.AMOUNT)
}