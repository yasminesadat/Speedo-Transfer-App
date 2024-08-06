package com.ys.speedotransferapp.ui.home

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.constants.AppRoutes.TRANSACTIONS_ROUTE
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.G10
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.G200
import com.ys.speedotransferapp.ui.theme.G40
import com.ys.speedotransferapp.ui.theme.G700
import com.ys.speedotransferapp.ui.theme.G900
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P50
import com.ys.speedotransferapp.ui.theme.S400
import com.ys.speedotransferapp.ui_model.Profile
import com.ys.speedotransferapp.ui_model.ServiceItem
import com.ys.speedotransferapp.ui_model.Transaction

@Composable
fun HomeScreen(
    navController: NavController,
    onLogout: () -> Unit,
    viewModel: HomeViewModel = HomeViewModel()
) {
    val userProfile = viewModel.userProfile.collectAsState().value
    val balance = viewModel.balance.collectAsState().value
    val transactions = viewModel.transactions.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ScreenHeader(userProfile)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = P300),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Current Balance",
                color = G0,
                modifier = Modifier.padding(top = 24.dp, start = 12.dp, bottom = 4.dp)
            )
            balance?.let {
                Text(
                    text = it,
                    color = G0,
                    modifier = Modifier.padding(bottom = 24.dp, start = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = G0),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Services",
                color = G700,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 4.dp, start = 12.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                for (service in viewModel.services)
                    Service(service)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Transactions",
                color = G700,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "View all",
                color = G200,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    navController.navigate(TRANSACTIONS_ROUTE)
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TransactionList(transactions)
    }
}

@Composable
fun ScreenHeader(userProfile: Profile?) {
    userProfile?.let {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CircularText(it.initials)
            Spacer(modifier = Modifier.width(8.dp))
            BasicText(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 24.sp)) {
                        withStyle(SpanStyle(color = P300)) {
                            append("Welcome back,")
                        }
                        append("\n")
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Medium,
                                color = G900,
                                fontSize = 16.sp
                            )
                        ) {
                            append(it.name)
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.notifications),
                tint = Unspecified,
                contentDescription = "notification icon",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun CircularText(text: String) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .clip(GenericShape { size, _ ->
                addOval(size.toRect())
            })
            .background(G40),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = G100,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Service(service: ServiceItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(G10)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(service.icon),
                contentDescription = "service icon",
                tint = S400,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(40.dp)
                    .padding(4.dp)
            )
        }
        Text(
            text = service.name,
            color = G900,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun TransactionList(transactions: List<Transaction>) {
    Card(
        colors = CardDefaults.cardColors(containerColor = G0),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            for (transaction in transactions.take(3)) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(68.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(P50)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(transaction.paymentProcessorIcon),
                                contentDescription = "payment processor icon",
                                tint = Unspecified,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(36.dp)
                                    .padding(4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = transaction.recipientName,
                                    color = G900,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = transaction.amount,
                                    color = P300,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Text(
                                text = "${transaction.paymentProcessor} . ${transaction.recipientDigits}",
                                color = G700
                            )
                            Text(
                                text = "${transaction.dateTime} - ${transaction.status}",
                                color = G100
                            )
                        }
                    }
                    HorizontalDivider(color = G40, thickness = 2.dp)
                }
            }
        }
    }
}
