package com.ys.speedotransferapp.ui.transfer

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.TransferInfo
import com.ys.speedotransferapp.ui.theme.G70
import com.ys.speedotransferapp.ui.theme.appTypography
import kotlinx.coroutines.launch

@Composable
fun ConfirmationStep(navController: NavController) {
    val viewModel = remember { TransferScreenViewModel() }
    val currencyViewModel = remember { CurrenciesViewModel() }
    val context = LocalContext.current
    val currencies by currencyViewModel.selectedOptionsList.collectAsState()

    LaunchedEffect(Unit) {
        currencyViewModel.loadSelectedCurrencyOptionsList(context, listOf("selected_option_index_1", "selected_option_index_2"))
    }

    LaunchedEffect(Unit) {
        viewModel.loadTransferDetails(context)
    }
    Log.d("ConfirmationStep", "Currencies: $currencies")
    val amount by viewModel.amount_sending.collectAsState()
    val recName by viewModel.recName.collectAsState()
    val recAccount by viewModel.recAccount.collectAsState()
    val amountDouble = if (amount.isNotEmpty()) amount.toDouble() else 0.0
    val convertedAmount = currencyViewModel.convertCurrency(amountDouble, currencies[0].curr_code, currencies[1].curr_code).toString()
    var recAccountString = "xxxx"+recAccount.takeLast(4)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Text("$amount ${currencies[0].curr_code}", style = appTypography.titleLarge)
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
                Text(text = "$convertedAmount ", style = appTypography.bodySmall)
                Text(text = currencies[1].curr_code, style = appTypography.bodySmall)
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.size(16.dp))
        TransferInfo(
            fromName = "Yasmine Atef",
            fromAccount = "Account xxxx1234",
            toName = recName,
            toAccount = "Account $recAccountString",
            icon = R.drawable.transfer_arrows
        )
}
}