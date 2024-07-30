package com.ys.speedotransferapp.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.signup.SignUpViewModel
import com.ys.speedotransferapp.ui.theme.D300
import com.ys.speedotransferapp.ui.theme.G10
import com.ys.speedotransferapp.ui.theme.G70
import com.ys.speedotransferapp.ui.theme.G700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    label: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    isPassword: Boolean = false,
    @DrawableRes trailingIcon: Int,
    iconDescription: String,
    modifier: Modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        Text(text = label, modifier = modifier)
        OutlinedTextField(
            value = value,
            label = { Text(text = hint) },
            onValueChange = onValueChanged,
            trailingIcon = {
                if (isPassword) {
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
            modifier = modifier,
        )
    }
}
@Composable
fun SpeedoTransferText(modifier: Modifier = Modifier) {
    Text(
        text = "Speedo Transfer",
        modifier = Modifier.padding(16.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall
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
        trailingIcon = R.drawable.user,
        iconDescription = "User icon"
    )
}