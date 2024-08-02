package com.ys.speedotransferapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.G40
import com.ys.speedotransferapp.ui.theme.G900
import com.ys.speedotransferapp.ui.theme.P300

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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircularText(viewModel.getInitials())
            Spacer(modifier = Modifier.padding(end = 8.dp))
            BasicText(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = P300, fontSize = 12.sp)) {
                        append("Welcome back,")
                    }
                    append("\n")
                    withStyle(
                        SpanStyle(
                            color = G900,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(viewModel.user)
                    }
                },
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

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}