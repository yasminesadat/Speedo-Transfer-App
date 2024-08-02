package com.ys.speedotransferapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
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
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.data.ServicesSource
import com.ys.speedotransferapp.model.ServiceItem
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

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = HomeViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .padding(horizontal = 12.dp)
    ) {
        ScreenHeader(viewModel)
        Spacer(modifier = Modifier.padding(5.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = P300),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Current Balance",
                color = G0,
                modifier = Modifier.padding(top = 24.dp, start = 12.dp, bottom =4.dp)
            )
            Text(
                text = viewModel.profile.balance,
                color = G0,
                modifier = Modifier.padding(bottom = 24.dp, start = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = G0),
            modifier = Modifier.fillMaxWidth()
        ){
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
            ){
              for(service in ServicesSource().getServices())
                  Service(service)
              }
            }
        Spacer(modifier = Modifier.padding(5.dp))
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
                fontWeight = FontWeight.Medium
            )
        }
        }
    }


@Composable
fun ScreenHeader(viewModel: HomeViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CircularText(viewModel.getInitials())
        Spacer(modifier = Modifier.padding(end = 8.dp))
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
                        append(viewModel.profile.firstAndSurname)
                    }
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.notifications),
            tint = Unspecified,
            contentDescription = "notification icon",
            modifier = Modifier
                .size(32.dp)
        )
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
        modifier = Modifier
            .padding(top = 8.dp,bottom = 4.dp)
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
                .padding(top= 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}