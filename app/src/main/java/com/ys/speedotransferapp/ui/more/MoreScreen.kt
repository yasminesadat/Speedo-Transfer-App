package com.ys.speedotransferapp.ui.more

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.common.Header
import com.ys.speedotransferapp.data.OptionsSource
import com.ys.speedotransferapp.navigation.AppRoutes.FAVOURITES_ROUTE
import com.ys.speedotransferapp.ui.theme.Black
import com.ys.speedotransferapp.ui.theme.DarkGrey
import com.ys.speedotransferapp.ui.theme.LightGrey

@Composable
fun MoreScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 32.dp)

    ) {
        Header("More", navController)

        val options = OptionsSource().getOptions()
        for (option in options) {
            Option(
                icon = option.icon,
                title = option.title,
                isLast = option.isLast,
                navController = navController
            )
        }
    }

}


@Composable
fun Option(
    @DrawableRes icon: Int,
    title: String,
    isLast: Boolean = false,
    navController: NavController
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    when (title) {
                        "Transfer From Website" -> {}
                        "Favourites" -> navController.navigate(FAVOURITES_ROUTE)
                        "Profile" -> {}
                        "Help" -> {}
                        "logout" -> {}
                    }
                }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
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
                    contentDescription = null,
                    tint = DarkGrey,
                    modifier = Modifier
                        .size(32.dp)
                        .rotate(-90f)
                )
            }
        }
        if (!isLast) {
            HorizontalDivider(
                Modifier.padding(all = 8.dp),
                color = LightGrey
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoreScreenPreview() {
    MoreScreen(rememberNavController())
}