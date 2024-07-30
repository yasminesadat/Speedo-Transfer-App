package com.ys.speedotransferapp.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.InputField
import com.ys.speedotransferapp.ui.common.SpeedoTransferText
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.Pink20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtraSignUpScreen(viewModel: SignUpViewModel, modifier: Modifier = Modifier) {
    val country by viewModel.country.collectAsState()
    val dateOfBirth by viewModel.dateOfBirth.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(G0, Pink20)
                )
            ),
        topBar = {
            TopAppBar(
                title = {

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)

        ) {
            SpeedoTransferText()
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Let's Complete your Profile")
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                value = country,
                label = "Country",
                hint = "Select your country",
                onValueChanged = { viewModel.setCountry(it) },
                isClickable = true,
                clickAction = { },
                trailingIcon = R.drawable.chevron_down,
                iconDescription = "chevron down icon"
            )

            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                value = dateOfBirth,
                label = "Date of Birth",
                hint = "DD/MM/YYYY",
                onValueChanged = { viewModel.setDateOfBirth(it) },
                trailingIcon = R.drawable.date,
                iconDescription = "Date icon"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = P300),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = country.isNotBlank() && dateOfBirth.isNotBlank()

            ) {
                Text(
                    text = "Continue",
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

@Composable
fun SideScreen(onDismiss: () -> Unit) {
    Popup(alignment = Alignment.CenterEnd, onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column {
                Text(text = "Select an Option", style = MaterialTheme.typography.bodyMedium)
                // Add your list items here
                Button(onClick = onDismiss) {
                    Text(text = "Close")
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExtraSignUpScreenPreview() {
      ExtraSignUpScreen(viewModel = SignUpViewModel())
}