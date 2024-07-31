package com.ys.speedotransferapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.data.BottomNavigationItemsSource
import com.ys.speedotransferapp.navigation.AppRoutes.FAVOURITES_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.HOME_ROUTE
import com.ys.speedotransferapp.navigation.AppRoutes.MORE_ROUTE
import com.ys.speedotransferapp.ui.favourite.FavouriteScreen
import com.ys.speedotransferapp.ui.home.HomeScreen
import com.ys.speedotransferapp.ui.more.MoreScreen
import com.ys.speedotransferapp.ui.theme.CosmicLatte
import com.ys.speedotransferapp.ui.theme.DarkCherry
import com.ys.speedotransferapp.ui.theme.DarkGrey
import com.ys.speedotransferapp.ui.theme.LightRose

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsState()

    Scaffold(
        bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                ) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    val destinations = BottomNavigationItemsSource().get()
                    destinations.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                viewModel.setItemIndex(index)
                                navController.navigate(item.route)
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    color = if (selectedItemIndex == index) DarkCherry else DarkGrey,
                                    fontSize =  9.sp
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = item.icon),
                                    contentDescription = item.label,
                                    modifier = Modifier.size(24.dp),
                                    tint = if (selectedItemIndex == index) DarkCherry else DarkGrey
                                )
                            },
                            interactionSource = MutableInteractionSource(),
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(CosmicLatte, LightRose)
                    )
                )
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = HOME_ROUTE,
            ) {
                composable(HOME_ROUTE) { HomeScreen() }
                composable(MORE_ROUTE) { MoreScreen(navController) }
                composable(FAVOURITES_ROUTE) {FavouriteScreen(navController)}
            }
        }
    }
}
