package com.ys.speedotransferapp.ui.more

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.data.OptionsSource
import com.ys.speedotransferapp.ui.theme.CosmicLatte
import com.ys.speedotransferapp.ui.theme.DarkGrey
import com.ys.speedotransferapp.ui.theme.LightRose

@Composable
fun MoreScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(CosmicLatte, LightRose)
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(top = 48.dp)

        ) {
            Header()
            val options = OptionsSource().getOptions()
            for (option in options) {
                Option(
                    icon = option.icon,
                    title = option.title,
                    isLast = option.isLast
                )
            }
        }

    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 48.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterStart),
            tint = Color.Black
        )
        Text(
            text = "More",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun Option(
    @DrawableRes icon: Int,
    title: String,
    isLast: Boolean = false
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(20.dp),
                tint = DarkGrey
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            if (!isLast) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron_down),
                    contentDescription = "click to choose option",
                    tint = DarkGrey,
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(-90f)
                )
            }
        }

        if (!isLast) {
            HorizontalDivider(Modifier.padding(all = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoreScreenPreview() {
    MoreScreen(rememberNavController())
}