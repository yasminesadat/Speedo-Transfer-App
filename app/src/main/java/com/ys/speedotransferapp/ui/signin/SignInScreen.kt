package com.ys.speedotransferapp.ui.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.ui.common.InputField
import com.ys.speedotransferapp.ui.common.SpeedoTransferText
import com.ys.speedotransferapp.ui.signup.SignUpViewModel
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.Pink20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(viewModel: SignInViewModel, modifier: Modifier =  Modifier) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(G0, Pink20)
                )
            ),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sign In",
                        modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp), textAlign = TextAlign.Center
                    )
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
            InputField(
                value = email,
                label = "Email",
                hint = "Enter your email address",
                onValueChanged = { viewModel.setEmail(it) },
                trailingIcon = R.drawable.email,
                iconDescription = "Email icon"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                value = password,
                label = "Password",
                hint = "Enter your password",
                onValueChanged = { viewModel.setPassword(it) },
                isPassword = true,
                trailingIcon = R.drawable.close_eye,
                iconDescription = "User icon"
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
                enabled = email.isNotBlank() && password.isNotBlank()

            ) {
                Text(
                    text = "Sign In",
                    style = TextStyle(
                        color = Color.White,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                    )
                )

            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                modifier = modifier
            ) {
                Text(
                    text = "Don't have an account?",
                    color = Color(0xff898886),
                    lineHeight = 9.38.em,
                    style = TextStyle(
                        fontSize = 16.sp))
                Text(
                    text = "Sign In",
                    color = Color(0xff871e35),
                    textDecoration = TextDecoration.Underline,
                    lineHeight = 8.12.em,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium))
            }
        }
    }
}