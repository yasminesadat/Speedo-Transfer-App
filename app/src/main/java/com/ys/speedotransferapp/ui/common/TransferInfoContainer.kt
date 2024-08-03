package com.ys.speedotransferapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.G40
import com.ys.speedotransferapp.ui.theme.G700
import com.ys.speedotransferapp.ui.theme.G900
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P50

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