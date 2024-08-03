package com.ys.speedotransferapp.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.ui.theme.G900

@Composable
fun CustomOutlinedTextField(
    header: String,
    value: String,
    onValueChange: (String) -> Unit, label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    borderColor: Color = Color.Gray,
    unfocusedBorderColor: Color = Color.LightGray,
    textColor: Color = Color.Black,
    labelColor: Color = Color.Gray,
    fontSize: Int = 16,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp)
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = header,
            style = TextStyle(color = Color.Gray, fontSize = 16.sp),
            modifier = Modifier.padding(top = 4.dp),
            color = G900
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                if (!isFocused && value.isEmpty()) {
                    Text(
                        text = label,
                        color = labelColor
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused },
            textStyle = TextStyle(color = textColor, fontSize = fontSize.sp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = unfocusedBorderColor
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = shape,
            singleLine = true
        )
    }
}
