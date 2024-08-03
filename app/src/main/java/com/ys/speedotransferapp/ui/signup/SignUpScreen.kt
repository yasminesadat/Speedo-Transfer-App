package com.ys.speedotransferapp.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.constants.AppRoutes
import com.ys.speedotransferapp.ui.common.CommonComposableViewModel
import com.ys.speedotransferapp.ui.common.InputField
import com.ys.speedotransferapp.ui.common.SpeedoTransferText
import com.ys.speedotransferapp.ui.theme.G0
import com.ys.speedotransferapp.ui.theme.P300
import com.ys.speedotransferapp.ui.theme.P20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = SignUpViewModel(), modifier: Modifier = Modifier) {
    val fullName by viewModel.fullName.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    var isPassError: Boolean = false
    val view_model = remember { CommonComposableViewModel() }
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(G0, P20)
                )
            ),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Sign up",
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
            InputField(
                viewModel = view_model,
                value = fullName,
                label = "Full Name",
                fieldId = "fullName",
                hint = "Enter your Full Name",
                onValueChanged = { viewModel.setFullName(it) },
                trailingIcon = R.drawable.user,
                iconDescription = "User icon",
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                viewModel = view_model,
                value = email,
                label = "Email",
                fieldId = "email",
                hint = "Enter your email address",
                onValueChanged = { viewModel.setEmail(it) },
                trailingIcon = R.drawable.email,
                iconDescription = "Email icon"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            isPassError = InputField(
                viewModel = view_model,
                value = password,
                label = "Password",
                fieldId = "password",
                hint = "Enter your password",
                onValueChanged = { viewModel.setPassword(it) },
                isPassword = true,
                trailingIcon = R.drawable.close_eye,
                iconDescription = "User icon"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = { if(view_model.errorMessages["password"] == null) navController.navigate(AppRoutes.EXTRA_SIGN_UP_ROUTE)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = P300),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = fullName.isNotBlank() && email.isNotBlank() && (password.isNotBlank() || isPassError)

            ) {
                Text(
                    text = "Sign Up",
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
                    text = "Already have an account?",
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
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier.clickable { navController.navigate(AppRoutes.SIGN_IN_ROUTE) })
            }
        }
        }
    }



@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    //SignUpScreen(SignUpViewModel())
}

