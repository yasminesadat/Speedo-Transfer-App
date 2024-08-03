package com.ys.speedotransferapp.ui.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.G40
import com.ys.speedotransferapp.ui.theme.G700
import com.ys.speedotransferapp.ui.theme.G900
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P50


@Composable
fun TransactionScreen(
    navController: NavController,
    viewModel: TransactionViewModel = TransactionViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Header(text = viewModel.getHeader(), navController = navController)

        Image(
            painter = painterResource(viewModel.getLargeIcon()),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            BasicText(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = G900,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(viewModel.transaction.amount)
                    }
                    append(" ")
                    withStyle(
                        SpanStyle(
                            color = P300,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(viewModel.transaction.currency)
                    }
                }
            )
        }
        TransferInfo(
            fromName = viewModel.transaction.senderName,
            fromAccount = viewModel.transaction.senderAccount,
            toName = viewModel.transaction.recipientName,
            toAccount = viewModel.transaction.recipientAccount,
            icon = viewModel.getIcon()
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = P50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            RowEntry(
                field = "Reference",
                value = viewModel.transaction.reference,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            )
            HorizontalDivider(color = G40, thickness = 2.dp)
            RowEntry(
                field = "Date",
                value = viewModel.transaction.dateTime,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            )
        }
    }
}


@Composable
fun TransferInfo(
    fromName: String,
    fromAccount: String,
    toName: String,
    toAccount: String,
    icon: Int
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(height = 250.dp, width = 500.dp)
    ) {
        TransferCard(
            name = fromName,
            account = fromAccount,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .size(height = 120.dp, width = 500.dp)
        )
        TransferCard(
            name = toName,
            account = toAccount,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .size(height = 120.dp, width = 500.dp)
        )
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun TransferCard(
    name: String,
    account: String,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = P50),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(GenericShape { size, _ ->
                        addOval(size.toRect())
                    })
                    .background(G40)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.bank),
                    contentDescription = "bank icon",
                    tint = G700,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(36.dp)
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            BasicText(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                        withStyle(
                            SpanStyle(
                                color = P300,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("From")
                        }
                        append("\n")
                        withStyle(
                            SpanStyle(
                                color = G900,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(name)
                        }
                        append("\n")
                        withStyle(
                            SpanStyle(
                                color = G100,
                                fontSize = 18.sp
                            )
                        ) {
                            append(account)
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun RowEntry(field: String, value: String, modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = field,
            color = G700,
            fontSize = 20.sp,
            modifier = Modifier
                .padding()
                .weight(1f)
        )
        SelectionContainer {
            Text(
                text = value,
                fontSize = 16.sp,
                color = G100
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransferInfoPreview() {
    TransactionScreen(rememberNavController())
}