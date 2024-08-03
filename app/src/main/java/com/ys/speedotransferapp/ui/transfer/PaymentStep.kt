package com.ys.speedotransferapp.ui.transfer

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.theme.appTypography

@Composable
fun PaymentStep(
    amount: Double,
    isSuccessful: Boolean,
    onBackToHome: () -> Unit
) {
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
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .requiredHeight(height = 263.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 126.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xfff3e9eb))
                            .padding(all = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 48.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xffe3e2e2))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bank),
                                contentDescription = "Icons/bank",
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 8.dp,
                                        y = 8.dp
                                    )
                                    .requiredSize(size = 32.dp)
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                        ) {
                            Text(
                                text = "From",
                                color = Color(0xff871e35),
                                lineHeight = 9.38.em,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                            ) {
                                Text(
                                    text = "Asmaa Dosuky",
                                    color = Color(0xff24221e),
                                    lineHeight = 7.5.em,
                                    style = TextStyle(
                                        fontSize = 20.sp
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                )
                                Text(
                                    text = "Account xxxx7890",
                                    color = Color(0xff898886),
                                    lineHeight = 9.38.em,
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 137.dp
                        )
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color(0xfff3e9eb))
                        .padding(all = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 48.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 48.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xffe3e2e2))
                        )
                        Image(
                            painter = painterResource(id = R.drawable.bank),
                            contentDescription = "Icons/bank",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 8.dp,
                                    y = 8.dp
                                )
                                .requiredSize(size = 32.dp)
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                    ) {
                        Text(
                            text = "To",
                            color = Color(0xff871e35),
                            lineHeight = 9.38.em,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                        ) {
                            Text(
                                text = "Jonathon Smith",
                                color = Color(0xff24221e),
                                lineHeight = 7.5.em,
                                style = TextStyle(
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                            Text(
                                text = "Account xxxx7890",
                                color = Color(0xff898886),
                                lineHeight = 9.38.em,
                                style = TextStyle(
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                        }
                    }
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
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 44.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff9f7815))
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.check_sucess),
                        contentDescription = "Group",
                        tint = Color.White,
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 14.dp,
                                y = 12.dp
                            )
                            .requiredWidth(width = 16.dp)
                            .requiredHeight(height = 20.dp)
                    )
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
                    Text(text = "48.4220 ", style = appTypography.bodySmall)
                    Text(text = "EGP", style = appTypography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}
