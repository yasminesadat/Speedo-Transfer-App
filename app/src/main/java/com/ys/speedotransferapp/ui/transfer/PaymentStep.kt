package com.ys.speedotransferapp.ui.transfer

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.TransferInfo
import com.ys.speedotransferapp.ui.theme.appTypography

@Composable
fun PaymentStep(
    navController: NavController
) {

    val context = LocalContext.current
    val viewModel = remember {
        TransferScreenViewModel(context.getSharedPreferences("auth_data", Context.MODE_PRIVATE)
            .getString("token", "")!!)
    }
    val currenciesViewModel = remember {
        CurrenciesViewModel()
    }

    LaunchedEffect(Unit) {
        currenciesViewModel.loadSelectedCurrencyOptionsList(context, listOf("selected_option_index_1", "selected_option_index_2"))
    }
    LaunchedEffect(Unit) {
        viewModel.loadTransferDetails(context)
    }
    val amount by viewModel.amount_sending.collectAsState()
    val amountDouble = if (amount.isNotEmpty()) amount.toDouble() else 0.0
    val recName by viewModel.recName.collectAsState()
    val recAccount by viewModel.recAccount.collectAsState()
    val currencies by currenciesViewModel.selectedOptionsList.collectAsState()
    var recAccountString = "xxxx"+recAccount.takeLast(4)
    val convertedAmount =
        currenciesViewModel.convertCurrency(amountDouble, currencies[0].curr_code, currencies[1].curr_code).toString()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.check_mark),
                contentDescription = "Check Mark",
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text("Your transfer was successful!", style = appTypography.titleLarge)
            Spacer(modifier = Modifier.size(16.dp))
            TransferInfo(
                fromName = "Yasmine Atef",
                fromAccount = "Account xxxx1234",
                toName = recName,
                toAccount = "Account $recAccountString",
                icon = R.drawable.success_small
            )

        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 148.dp,
                    y = 111.dp
                )
                .requiredSize(size = 44.dp)
        ) {
        }
    }
    Spacer(modifier = Modifier.size(16.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {
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
}
