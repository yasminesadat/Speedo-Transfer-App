package com.ys.speedotransferapp.ui.transfer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.TransferInfo
import com.ys.speedotransferapp.ui.theme.G70
import com.ys.speedotransferapp.ui.theme.appTypography

@Composable
fun ConfirmationStep(
    amount: Double,
    onConfirm: () -> Unit,
    onPrevious: () -> Unit
) {
    val viewModel = remember {
        TransferScreenViewModel()
    }
    val selectedOption by viewModel.selectedOption.collectAsState()
    val amountSend by viewModel.amount_sending.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("1000 USD", style = appTypography.titleLarge)
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Transfer amount", style = appTypography.bodySmall, color = G70)
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text(text = "Total amount", style = appTypography.bodyMedium)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "48.4220 ", style = appTypography.bodySmall)
                Text(text = "EGP", style = appTypography.bodySmall)
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.size(16.dp))
        TransferInfo(
            fromName = "Yasmine Atef",
            fromAccount = "Account xxxx1234",
            toName = "Ahmed Bakr",
            toAccount = "Account xxxx1234",
            icon = R.drawable.transfer_arrows
        )
}
}