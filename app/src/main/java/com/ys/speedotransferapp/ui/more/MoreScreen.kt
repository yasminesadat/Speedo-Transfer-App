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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.constants.AppConstants.EMAIL
import com.ys.speedotransferapp.constants.AppConstants.NUMBER
import com.ys.speedotransferapp.constants.AppRoutes.FAVOURITES_ROUTE
import com.ys.speedotransferapp.data.OptionsSource
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.G200
import com.ys.speedotransferapp.ui.theme.G40
import com.ys.speedotransferapp.ui.theme.G900
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P50

@Composable
fun MoreScreen(
    navController: NavController,
    onLogout: () -> Unit,
    viewModel: MoreViewModel = viewModel(),
) {
    val context = LocalContext.current

    if (viewModel.showHelpBottomSheet) {
        ShowHelp(viewModel, context)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 32.dp)

    ) {
        Header(
            text = "More",
            navController = navController
        )

        val options = OptionsSource().getOptions()
        for (option in options) {
            Option(
                icon = option.icon,
                title = option.title,
                onLogout = onLogout,
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
    onLogout: () -> Unit,
    navController: NavController,
    viewModel: MoreViewModel
) {
    val token = viewModel.loadToken(LocalContext.current)
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

                        "logout" -> {onLogout(); viewModel.logout(token!!)}
                    }
                }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
                tint = G200
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
            )

            if (!isLast) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                    contentDescription = null,
                    tint = G200,
                    modifier = Modifier
                        .size(16.dp)
                )
            }
        }
        if (!isLast) {
            HorizontalDivider(
                Modifier.padding(all = 8.dp),
                color = G40
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowHelp(
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
                            data = Uri.parse("mailto: $EMAIL")
                        }
                        context.startActivity(intent)
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 4.dp)
                        .fillMaxWidth()
                        .height(124.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(P50)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.email),
                            contentDescription = "mail icon",
                            tint = P300,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(36.dp)
                                .padding(4.dp)
                        )
                    }
                    Text(
                        text = "Send Email",
                        color = G900,
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
                            data = Uri.parse("tel:$NUMBER")
                        }
                        context.startActivity(intent)
                    },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 4.dp)
                        .fillMaxWidth()
                        .height(124.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(P50)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.call),
                            contentDescription = "call icon",
                            tint = P300,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(36.dp)
                                .padding(4.dp)
                        )
                    }
                    Text(
                        text = "Call Us",
                        color = G900,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = NUMBER.toString(),
                        color = P300,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
