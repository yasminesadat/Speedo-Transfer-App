package com.ys.speedotransferapp.ui.transactions

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.constants.AppRoutes.TRANSACTION_ROUTE
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.G200
import com.ys.speedotransferapp.ui.theme.G700
import com.ys.speedotransferapp.ui.theme.G900
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P50
import com.ys.speedotransferapp.ui_model.Transaction

@Composable
fun TransactionsScreen(
    navController: NavController,
    viewModel: TransactionsViewModel = TransactionsViewModel(
        LocalContext.current.getSharedPreferences("auth_data", Context.MODE_PRIVATE)
        .getString("token", "")!!),
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
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Your Last Transactions",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 8.dp)
            )
        }
        val transactions = viewModel.transactions.collectAsLazyPagingItems()
        LazyColumn {
            items(transactions.itemCount) {
                TransactionCard(transactions[it]!!, viewModel, navController)
            }
        }
    }
}




@Composable
fun TransactionCard(
    transaction: Transaction,
    viewModel: TransactionsViewModel,
    navController: NavController
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = G0),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { navController.navigate("$TRANSACTION_ROUTE/${transaction.reference}") }
    ) {
        Row(
            modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(P50)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(viewModel.getIcon()),
                    contentDescription = null,
                    tint = P300,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(36.dp)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.recipientName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 3.sp,
                    color = G900
                )
                BasicText(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = G700,
                                fontSize = 14.sp
                            )
                        ) {
                            append(
                                viewModel.getCardDetails(transaction)
                            )
                        }
                        append("\n")
                        withStyle(
                            SpanStyle(
                                color = G100,
                                fontSize = 14.sp
                            )
                        ) {
                            append(transaction.dateTime)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transaction.amount,
                    color = P300,
                    fontSize = 18.sp,
                    lineHeight = 1.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                    contentDescription = null,
                    tint = G200,
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(viewModel.getBackgroundColor(transaction.status))

                )
                {
                    Text(
                        text = transaction.status,
                        fontSize = 10.sp,
                        color = viewModel.getTextColor(transaction.status),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionsScreenPreview() {
    TransactionsScreen(navController = rememberNavController())
}