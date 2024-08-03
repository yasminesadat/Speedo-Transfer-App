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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.CustomOutlinedTextField
import com.ys.speedotransferapp.ui.common.Header
import com.ys.speedotransferapp.ui.theme.D300
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.G40
import com.ys.speedotransferapp.ui.theme.G700
import com.ys.speedotransferapp.ui.theme.G900
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P50
import com.ys.speedotransferapp.ui_model.FavouriteItem

@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel: FavouriteViewModel = viewModel()
) {
    if (viewModel.showBottomSheet) {
        BottomSheet(
            onDismiss = { viewModel.showBottomSheet(false) },
            onSave = { viewModel.updateFavourite() },
            viewModel = viewModel
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
            FavouritesCard(favouriteItem, viewModel)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun FavouriteListNoIcon(viewModel: FavouriteViewModel, onItemClick: (FavouriteItem) -> Unit) {
    val favourites = viewModel.favourites.collectAsState().value
    LazyColumn {
        items(favourites) { favouriteItem ->
            FavouritesCardNoIcons(
                favouriteItem,
                onClick = { onItemClick(favouriteItem) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun FavouritesCard(favourite: FavouriteItem, viewModel: FavouriteViewModel) {
    Card(
        colors = CardDefaults.cardColors(containerColor = P50),
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
                    .background(G40)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.bank),
                    contentDescription = "bank icon",
                    tint = G700,
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
                    color = G900,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Text(
                    text = favourite.accountNumber,
                    color = G100,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = ImageVector.vectorResource(R.drawable.edit),
                contentDescription = "edit icon",
                tint = G100,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
                    .clickable {
                        viewModel.showBottomSheet(true)
                        viewModel.updateSelectedFavourite(favourite)
                    })
            Icon(imageVector = ImageVector.vectorResource(R.drawable.delete),
                contentDescription = "delete icon",
                tint = D300,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { viewModel.deleteFavourite(favourite) })
        }
    }
}


@Composable
fun FavouritesCardNoIcons(favourite: FavouriteItem, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = P50),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(onClick = onClick)
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
                    .background(G0)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.bank),
                    contentDescription = "bank icon",
                    tint = G700,
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
                    color = G900,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Text(
                    text = favourite.accountNumber,
                    color = G100,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    viewModel: FavouriteViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        windowInsets = WindowInsets.systemBars
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.edit),
                    contentDescription = "edit icon",
                    tint = P300,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Edit",
                    fontSize = 24.sp
                )

            }

            CustomOutlinedTextField(
                header = "Recipient Name",
                value = viewModel.name,
                onValueChange = { viewModel.updateName(it) },
                label = "Enter Cardholder Name",
            )

            CustomOutlinedTextField(
                header = "Recipient Account",
                value = viewModel.accountNumber,
                onValueChange = { viewModel.updateAccountNumber(it) },
                label = "Enter Cardholder Account Number",
                keyboardType = KeyboardType.Number
            )
            Button(
                onClick = {
                    onSave()
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = P300),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = viewModel.name.isNotBlank() && viewModel.accountNumber.isNotBlank()
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
