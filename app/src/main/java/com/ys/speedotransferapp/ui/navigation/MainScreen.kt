package com.ys.speedotransferapp.ui.navigation

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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.data.BottomNavigationItemsSource
import com.ys.speedotransferapp.constants.AppRoutes.FAVOURITES_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.HOME_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.MORE_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.TRANSACTIONS_ROUTE

import com.ys.speedotransferapp.constants.AppRoutes.TRANSACTION_ROUTE
import com.ys.speedotransferapp.constants.AppRoutes.TRANSFER_ROUTE

import com.ys.speedotransferapp.ui.favourite.FavouriteScreen
import com.ys.speedotransferapp.ui.home.HomeScreen
import com.ys.speedotransferapp.ui.more.MoreScreen
import com.ys.speedotransferapp.ui.theme.CosmicLatte
import com.ys.speedotransferapp.ui.theme.G200
import com.ys.speedotransferapp.ui.theme.P20
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.transaction.TransactionScreen
import com.ys.speedotransferapp.ui.transactions.TransactionsScreen
import com.ys.speedotransferapp.ui.transfer.TransferScreen

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {
                Spacer(modifier = Modifier.padding(8.dp))

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                LaunchedEffect(currentRoute) {
                    viewModel.updateItemIndex(currentRoute)
                }

                val destinations = BottomNavigationItemsSource().get()
                destinations.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = viewModel.selectedItemIndex == index,
                        onClick = {
                            viewModel.setItemIndex(index)
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(
                                text = item.label,
                                color = if (viewModel.selectedItemIndex == index) P300 else G200,
                                fontSize = 9.sp
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = item.icon),
                                contentDescription = item.label,
                                modifier = Modifier.size(24.dp),
                                tint = if (viewModel.selectedItemIndex == index) P300 else G200
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
                        colors = listOf(CosmicLatte, P20)
                    )
                )
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = HOME_ROUTE,
            ) {

                composable(HOME_ROUTE) { HomeScreen(navController) }
                composable(TRANSFER_ROUTE) { TransferScreen(navController) }
                composable(MORE_ROUTE) { MoreScreen(navController) }
                composable(FAVOURITES_ROUTE) { FavouriteScreen(navController) }
                composable(TRANSACTIONS_ROUTE) { TransactionsScreen(navController) }
                composable(TRANSACTION_ROUTE) { TransactionScreen(navController) }
            }
        }
    }
}
