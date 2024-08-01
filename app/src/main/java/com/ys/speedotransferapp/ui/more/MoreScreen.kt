package com.ys.speedotransferapp.ui.more

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.data.OptionsSource
import com.ys.speedotransferapp.navigation.AppRoutes.FAVOURITES_ROUTE
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.Black
import com.ys.speedotransferapp.ui.theme.DarkCherry
import com.ys.speedotransferapp.ui.theme.DarkGrey
import com.ys.speedotransferapp.ui.theme.LightGrey
import com.ys.speedotransferapp.ui.theme.SoftPink

@Composable
fun MoreScreen(
    navController: NavController,
    viewModel: MoreViewModel = viewModel()
) {
    val context = LocalContext.current
    val showHelpBottomSheet by viewModel.showHelpBottomSheet.collectAsState()

    if (showHelpBottomSheet) {
        showHelp(viewModel, context)
    }
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
                navController = navController,
                viewModel = viewModel
            )
        }
    }

}


@Composable
fun Option(
    @DrawableRes icon: Int,
    title: String,
    isLast: Boolean = false,
    navController: NavController,
    viewModel: MoreViewModel
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    when (title) {
                        "Transfer From Website" -> {}
                        "Favourites" -> navController.navigate(FAVOURITES_ROUTE)
                        "Profile" -> {}
                        "Help" -> {
                            viewModel.showHelpBottomSheet(true)
                        }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showHelp(
    viewModel: MoreViewModel,
    context: Context
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { viewModel.showHelpBottomSheet(false) },
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp)
                .padding(vertical = 24.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
                    .clickable {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:help@speedo.com")
                        }
                        context.startActivity(intent)
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top =16.dp, bottom = 4.dp)
                        .fillMaxWidth()
                        .height(124.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(SoftPink)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.email),
                            contentDescription = "mail icon",
                            tint = DarkCherry,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(36.dp)
                                .padding(4.dp)
                        )
                    }
                    Text(
                        text = "Send Email",
                        color = Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 8.dp)
                    )
                }
            }
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
                    .clickable {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:1234")
                        }
                        context.startActivity(intent)
                    },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top =16.dp, bottom = 4.dp)
                        .fillMaxWidth()
                        .height(124.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(SoftPink)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.call),
                            contentDescription = "call icon",
                            tint = DarkCherry,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(36.dp)
                                .padding(4.dp)
                        )
                    }
                    Text(
                        text = "Call Us",
                        color = Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = "1234",
                        color = DarkCherry,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoreScreenPreview() {
    MoreScreen(rememberNavController())
}