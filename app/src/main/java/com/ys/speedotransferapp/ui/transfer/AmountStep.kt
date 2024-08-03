package com.ys.speedotransferapp.ui.transfer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui_model.FavouriteItem
import com.ys.speedotransferapp.ui.common.CommonComposableViewModel
import com.ys.speedotransferapp.ui.common.InputField
import com.ys.speedotransferapp.ui.favourite.FavouriteListNoIcon
import com.ys.speedotransferapp.ui.favourite.FavouriteViewModel
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.G100
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.appTypography

@Composable
fun AmountStep(onAmountEntered: (Double) -> Unit) {
    val viewModel = remember {
        TransferScreenViewModel()
    }

    val viewModelFav = remember {
        FavouriteViewModel()
    }
    val amount_send by viewModel.amount_sending.collectAsState()
    val recName by viewModel.recName.collectAsState()
    val recAccount by viewModel.recAccount.collectAsState()

    if (viewModelFav.showBottomSheet) {
        BottomSheet(
            onDismiss = { viewModelFav.showBottomSheet(false) },
            onFavoriteSelected = { selectedFavorite ->
                viewModel.onRecNameChange(selectedFavorite.name)
                viewModel.onRecAccountChange(selectedFavorite.accountNumber)
            }
        )
    }

    Column {
        Text(text = "How much are you sending?", style = appTypography.titleLarge)
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Choose Currency", style = appTypography.bodyMedium)
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .wrapContentSize()  // Fill the width instead of size to avoid clipping issues
                .clip(RoundedCornerShape(8.dp))  // Adjust the radius as needed
                .background(G0)  // Use your desired background color
        ) {
            Column(
                modifier = Modifier.padding(16.dp)  // Adjust padding inside the box as needed
            ) {
                Text(text = "1 USD = 0.74 GBP", style = appTypography.bodyMedium)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "Rate guaranteed (2h)", style = appTypography.bodyMedium, color = G100)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "You Send", style = appTypography.bodySmall)
                Spacer(modifier = Modifier.size(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SimpleDropdown(TransferScreenViewModel())

                    OutlinedTextField(
                        value = amount_send,
                        onValueChange = { viewModel.onAmountSendChange(it) },
                        label = { Text("Enter Amount") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "Recipient Gets", style = appTypography.bodySmall)
                Spacer(modifier = Modifier.size(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SimpleDropdown(TransferScreenViewModel())

                    OutlinedTextField(
                        value = amount_send,
                        onValueChange = { viewModel.onAmountSendChange(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        readOnly = true,
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(text = "Recipient Information", style = appTypography.bodyMedium)
            Row(
                modifier = Modifier.clickable { viewModelFav.showBottomSheet(true) },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "Favorite",
                    tint = P300
                )
                Text(text = "Favourite", style = appTypography.bodyMedium, color = P300)
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = P300,
                    modifier = Modifier
                        .graphicsLayer(scaleX = -1f)
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        InputField(
            viewModel = CommonComposableViewModel(),
            value = recName,
            fieldId = "recipent_name",
            label = "Recipient Name",
            hint = "Enter Recipient Name",
            onValueChanged = { viewModel.onRecNameChange(it) },
            iconDescription = "",
            modifier = Modifier.fillMaxWidth()
        )

        InputField(
            viewModel = CommonComposableViewModel(),
            value = recAccount,
            fieldId = "recipent_account",
            label = "Recipient Account",
            hint = "Enter Recipient Account Number",
            onValueChanged = { viewModel.onRecAccountChange(it) },
            iconDescription = "",
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    onFavoriteSelected: (FavouriteItem) -> Unit
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
        )  {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.favorite),
                    contentDescription = "favorite icon",
                    tint = P300,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Favourite List",
                    fontSize = 24.sp,
                    color = P300
                )

            }
            FavouriteListNoIcon(
                viewModel = FavouriteViewModel(),
                onItemClick = { selectedFavorite ->
                    onFavoriteSelected(selectedFavorite)
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun SimpleDropdown(viewModel: TransferScreenViewModel) {
    val selectedOption by viewModel.selectedOption.collectAsState()
    val isExpanded by viewModel.isExpanded.collectAsState()

    Row(
        modifier = Modifier
            .clickable { viewModel.onDropdownClicked() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        selectedOption.let { option ->
            Image(
                painter = painterResource(id = option.curr_icon),
                contentDescription = "Flag of ${option.curr_name}",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = option.curr_code)
        }
        Icon(
            painter = painterResource(id = R.drawable.chevron_down),
            contentDescription = "Dropdown Arrow"
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { viewModel.onDropdownClicked() }
        ) {
            viewModel.options.forEach { option ->
                DropdownMenuItem(onClick = { viewModel.onOptionSelected(option) }, text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = option.curr_icon),
                            contentDescription = "Flag of ${option.curr_name}",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "${option.curr_code} - ${option.curr_name}")
                    }
                })

            }
        }
    }
}