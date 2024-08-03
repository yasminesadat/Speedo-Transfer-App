package com.ys.speedotransferapp.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    viewModel: CommonComposableViewModel,
    value: String,
    fieldId: String,
    label: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    isPassword: Boolean = false,
    isClickable: Boolean = false,
    readOnly: Boolean = false,
    clickAction: (() -> Unit)? = null,
    @DrawableRes trailingIcon: Int = 0,
    iconDescription: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp)
): Boolean {
    val colors = OutlinedTextFieldDefaults.colors(
        unfocusedContainerColor = G10,
        unfocusedLabelColor = G70,
        unfocusedBorderColor = G70,
        unfocusedTrailingIconColor = G70,
        focusedBorderColor = G700,
        errorBorderColor = D300,
        errorTrailingIconColor = D300,
        errorSupportingTextColor = D300
    )

    Column {
        Text(text = label, modifier = modifier, style = appTypography.bodySmall)
        var isFocused by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = value,
            label = { if (value.isEmpty() && !isFocused) Text(text = hint) },
            onValueChange = { newValue ->
                onValueChanged(newValue)
                viewModel.onValueChanged(fieldId, newValue, isPassword)
            },
            trailingIcon = {
                if (isClickable && clickAction != null) {
                    IconButton(onClick = { clickAction() }) {
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            contentDescription = iconDescription
                        )
                    }
                } else if (isPassword) {
                    IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = painterResource(id = if (viewModel.passwordVisible) R.drawable.open_eye else R.drawable.close_eye),
                            contentDescription = if (viewModel.passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }else if (trailingIcon == 0) {
                    // Do nothing
                }
                else {
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = iconDescription
                    )
                }
            },
            visualTransformation = if (isPassword && !viewModel.passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else keyboardType
            ),
            colors = colors,
            isError = viewModel.errorMessages[fieldId] != null,
            supportingText = {
                viewModel.errorMessages[fieldId]?.let { error ->
                    Text(
                        text = error,
                        color = D300,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            readOnly = readOnly,
            modifier = modifier.onFocusChanged { isFocused = it.isFocused },
        )
    }

    return viewModel.errorMessages[fieldId] == null
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    clickAction: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            modifier = modifier,
            containerColor = Color.White,
            windowInsets = WindowInsets.systemBars, // Adjust insets as needed
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight() // This makes the sheet cover the entire height
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier
                    .clickable { clickAction("Egypt"); onDismiss() }
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.egypt),
                        contentDescription = "Egypt flag"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Egypt")
                }
                Row(modifier = Modifier
                    .clickable { clickAction("United States"); onDismiss() }
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.united_states),
                        contentDescription = "United States flag"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "United States")
                }
            }
        }
    }
}

@Composable
fun SpeedoTransferText(modifier: Modifier = Modifier) {
    Text(
        text = "Speedo Transfer",
        modifier = modifier.padding(16.dp),
        textAlign = TextAlign.Center,
        style = appTypography.headlineLarge
    )
}



@Preview(showBackground = true)
@Composable
private fun InputFieldPreview() {
    val viewModel = remember { CommonComposableViewModel() }
    InputField(
        viewModel = viewModel,
        fieldId = "firstName",
        value = "",
        label = "First Name",
        hint = "Enter your name",
        onValueChanged = { },
        isPassword = true,
        trailingIcon = R.drawable.user,
        iconDescription = "User icon"
    )
}