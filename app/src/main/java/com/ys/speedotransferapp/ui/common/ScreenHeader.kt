package com.ys.speedotransferapp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.theme.G900

@Composable
fun Header(
    text: String,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterStart)
                .padding(start = 8.dp)
                .clickable {
                    navController.popBackStack()
                }
            ,
            tint = G900
        )
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
