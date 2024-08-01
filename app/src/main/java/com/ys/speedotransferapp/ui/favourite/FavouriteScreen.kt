package com.ys.speedotransferapp.ui.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.model.FavouriteItem
import com.ys.speedotransferapp.ui.common.CustomOutlinedTextField
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.Black
import com.ys.speedotransferapp.ui.theme.CharcoalGrey
import com.ys.speedotransferapp.ui.theme.DarkCherry
import com.ys.speedotransferapp.ui.theme.GarlicBeige
import com.ys.speedotransferapp.ui.theme.Grey
import com.ys.speedotransferapp.ui.theme.LightGrey
import com.ys.speedotransferapp.ui.theme.Red
import com.ys.speedotransferapp.ui.theme.SoftPink

@Composable
fun FavouriteScreen(
    navController: NavController, viewModel: FavouriteViewModel = viewModel()
) {
    val showBottomSheet by viewModel.showBottomSheet.collectAsState()
    val selectedFavourite by viewModel.selectedFavourite.collectAsState()

    if (showBottomSheet) {
        BottomSheet(
            favourite = selectedFavourite!!,
            onDismiss = { viewModel.showBottomSheet(false) },
            onSave = { old, new -> viewModel.updateFavourite(old, new) }
        )
    }
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
        FavouriteList(viewModel)
    }
}


@Composable
fun FavouriteList(viewModel: FavouriteViewModel) {
    val favourites = viewModel.favourites.collectAsState().value
    LazyColumn {
        items(favourites) { favouriteItem ->
            FavouritesItem(favouriteItem, viewModel)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun FavouritesItem(favourite: FavouriteItem, viewModel: FavouriteViewModel) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
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
                        .size(36.dp)
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = favourite.name,
                    color = Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Text(
                    text = favourite.accountNumber,
                    color = CharcoalGrey,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = ImageVector.vectorResource(R.drawable.edit),
                contentDescription = "edit icon",
                tint = Grey,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
                    .clickable {
                        viewModel.showBottomSheet(true)
                        viewModel.setSelectedFavorite(favourite)
                    })
            Icon(imageVector = ImageVector.vectorResource(R.drawable.delete),
                contentDescription = "delete icon",
                tint = Red,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { viewModel.deleteFavourite(favourite) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    favourite: FavouriteItem,
    onDismiss: () -> Unit,
    onSave: (FavouriteItem, FavouriteItem) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        windowInsets = WindowInsets.systemBars
    ) {
        // not working bottom sheet height
        val screenHeight = LocalConfiguration.current.screenHeightDp
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn((screenHeight).dp, screenHeight.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.edit),
                    contentDescription = "edit icon",
                    tint = DarkCherry,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Edit",
                    fontSize = 24.sp
                )

            }
            var name by remember { mutableStateOf("") }
            var accountNumber by remember { mutableStateOf("") }

            CustomOutlinedTextField(
                header = "Recipient Name",
                value = name,
                onValueChange = { name = it },
                label = "Enter Cardholder Name",
            )

            CustomOutlinedTextField(
                header = "Recipient Account",
                value = accountNumber,
                onValueChange = { accountNumber = it.filter { field -> field.isDigit() } },
                label = "Enter Cardholder Account Number",
                keyboardType = KeyboardType.Number
            )
            Button(
                onClick = {
                    onSave(favourite, FavouriteItem(name, accountNumber))
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkCherry),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = name.isNotBlank() && accountNumber.isNotBlank()
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    style = TextStyle(
                        color = Color.White,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun FavouriteScreenPreview() {
    FavouriteScreen(rememberNavController())
}