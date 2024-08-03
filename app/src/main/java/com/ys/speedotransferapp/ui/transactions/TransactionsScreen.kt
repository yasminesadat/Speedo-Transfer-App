package com.ys.speedotransferapp.ui.transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.more.MoreViewModel

@Composable
fun TransactionsScreen(
    navController: NavController,
    viewModel: MoreViewModel = viewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 32.dp)

    ) {
        Header(
            text = "Transactions",
            navController = navController,
        )

    }
}