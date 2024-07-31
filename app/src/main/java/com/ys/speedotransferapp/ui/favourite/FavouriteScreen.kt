package com.ys.speedotransferapp.ui.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.common.Header
import com.ys.speedotransferapp.ui.theme.Black

@Composable
fun FavouriteScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 32.dp)

    ) {
        Header("Favourite", navController)
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Your favourite list",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        FavouriteList()
    }

}

@Composable
fun FavouriteList() {

}


@Preview(showBackground = true)
@Composable
private fun FavouriteScreenPreview() {
    FavouriteScreen(rememberNavController())
}
