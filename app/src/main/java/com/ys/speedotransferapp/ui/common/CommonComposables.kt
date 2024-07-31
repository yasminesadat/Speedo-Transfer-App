package com.ys.speedotransferapp.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    label: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    isPassword: Boolean = false,
    isClickable: Boolean = false,
    clickAction: (() -> Unit)? = null,
    @DrawableRes trailingIcon: Int,
    iconDescription: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp)
): Boolean {
    var passwordVisible by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedLabel by remember { mutableStateOf(label) }

    Column {
        Text(text = label, modifier = modifier)
        OutlinedTextField(
            value = selectedLabel,
            label = { Text(text = hint) },
            onValueChange = { newValue ->
                onValueChanged(newValue)
                if (isPassword) {
                    errorMessage = validatePassword(newValue)
                }
            },
            trailingIcon = {
                if (isClickable && clickAction != null) {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            contentDescription = iconDescription
                        )
                    }
                } else if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = if (passwordVisible) R.drawable.open_eye else R.drawable.close_eye),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = iconDescription
                    )
                }
            },
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = G10,
                unfocusedLabelColor = G70,
                unfocusedBorderColor = G70,
                unfocusedTrailingIconColor = G70,
                focusedBorderColor = G700,
                errorBorderColor = D300,
                errorTrailingIconColor = D300,
                errorSupportingTextColor = D300
            ),
            isError = errorMessage != null,
            supportingText = {
                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = D300,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            modifier = modifier,
        )
    }

    if (showBottomSheet) {
        BottomSheet(
            showBottomSheet = showBottomSheet,
            onDismiss = { showBottomSheet = false },
            clickAction = { selectedLabel = it }
        )
    }
    return errorMessage == null
}

fun validatePassword(password: String): String? {
    if (password.length < 6) {
        return "Password must be at least 6 characters long"
    }
    if (!password.any { it.isUpperCase() }) {
        return "Password must contain at least one uppercase letter"
    }
    if (!password.any { it.isLowerCase() }) {
        return "Password must contain at least one lowercase letter"
    }
    if (!password.any { it in "!@#$%^&*()_+-=[]{}|;:,.<>?" }) {
        return "Password must contain at least one special character"
    }
    return null // Password is valid
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
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.clickable { clickAction("Egypt") }) {
                    Image(
                        painter = painterResource(id = R.drawable.egypt),
                        contentDescription = "Egypt flag"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Egypt")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.clickable { clickAction("United States") }) {
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
    InputField(
        value = "",
        label = "First Name",
        hint = "Enter your name",
        onValueChanged = { },
        isPassword = true,
        trailingIcon = R.drawable.user,
        iconDescription = "User icon"
    )
}