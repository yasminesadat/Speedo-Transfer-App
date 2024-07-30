package com.ys.speedotransferapp.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ys.speedotransferapp.R

@Composable
fun InputField(
    label: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    @DrawableRes trailingIcon: Int,
    iconDescription: String,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = label)
        OutlinedTextField(
            value = hint,
            onValueChange = onValueChanged,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = iconDescription
                )
            },
            modifier = modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun InputFieldPreview() {
    InputField(
        label = "First Name",
        hint = "Enter your name",
        onValueChanged = { },
        trailingIcon = R.drawable.user,
        iconDescription = "User icon"
    )
}