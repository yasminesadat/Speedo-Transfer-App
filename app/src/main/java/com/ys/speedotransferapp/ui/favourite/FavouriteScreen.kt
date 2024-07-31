package com.ys.speedotransferapp.ui.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.common.Header
import com.ys.speedotransferapp.model.FavouriteItem
import com.ys.speedotransferapp.ui.theme.Black
import com.ys.speedotransferapp.ui.theme.CharcoalGrey
import com.ys.speedotransferapp.ui.theme.GarlicBeige
import com.ys.speedotransferapp.ui.theme.Grey
import com.ys.speedotransferapp.ui.theme.LightGrey
import com.ys.speedotransferapp.ui.theme.Red
import com.ys.speedotransferapp.ui.theme.SoftPink
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel: FavouriteViewModel = viewModel()
) {
    val favourites = viewModel.favourites
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
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 8.dp)

            )
        }
        FavouriteList(favourites)
    }

}

@Composable
fun FavouriteList(favourites: StateFlow<List<FavouriteItem>>) {
    LazyColumn {
        items(favourites.value) {
            FavouritesItem(it)
        }
    }

}

@Composable
fun FavouritesItem(item: FavouriteItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(GenericShape { size, _ ->
                        addOval(size.toRect())
                    })
                    .background(LightGrey)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.bank),
                    contentDescription = "bank icon",
                    tint = GarlicBeige,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(32.dp)
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = item.name,
                    color = Black,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = item.accountNumber,
                    color = CharcoalGrey,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.edit),
                contentDescription = "bank icon",
                tint = Grey,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.delete),
                contentDescription = "bank icon",
                tint = Red,
                modifier = Modifier.size(24.dp)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun FavouriteScreenPreview() {
    FavouriteScreen(rememberNavController())
}
