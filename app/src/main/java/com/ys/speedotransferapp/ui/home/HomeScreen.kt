package com.ys.speedotransferapp.ui.home

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ys.speedotransferapp.data.NavigationItemsSource
import com.ys.speedotransferapp.ui.theme.DarkCherry
import com.ys.speedotransferapp.ui.theme.DarkGrey

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(),
) {
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsState()
    Scaffold(bottomBar = {
        Box(modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))) {
                NavigationBar {
                    val destinations = NavigationItemsSource().get()
                    destinations.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                viewModel.setItemIndex(index)
                                navController.navigate(item.route)
                            },
                            label = {
                                Text(
                                    text = item.iconText,
                                    color = if (selectedItemIndex == index) DarkCherry else DarkGrey,
                                    fontSize = 10.sp
                                )
                            },
                            icon = {
                                Icon(
                                    painter = if (selectedItemIndex == index) painterResource(id = item.selectedIcon)
                                    else painterResource(item.unselectedIcon),
                                    contentDescription = item.iconText,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )

                            },
                            interactionSource = MutableInteractionSource(),
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }

        }

    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text("HOME")
        }
    }
}

