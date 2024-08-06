package com.ys.speedotransferapp.ui.signin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = SignInViewModel(),
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val loginResult by viewModel.loginResult.collectAsState()
    val view_model = remember { CommonComposableViewModel() }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var token = viewModel.loadToken(context)
    val loginResponse by viewModel.loginResult.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(G0, P20)
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
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        LaunchedEffect(loginResult) {
            Log.d("SignInScreen", "loginResult: $loginResult")
            loginResult?.let {
                if (it.isSuccess) {
                    onLoginSuccess()
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = it.exceptionOrNull()?.message ?: "Login failed"
                        )
                    }
                }
            }
        }

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
                viewModel = view_model,
                value = email,
                fieldId = "email",
                label = "Email",
                hint = "Enter your email address",
                onValueChanged = { viewModel.setEmail(it) },
                trailingIcon = R.drawable.email,
                iconDescription = "Email icon"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            val isPassError = InputField(
                viewModel = view_model,
                value = password,
                fieldId = "password_signin",
                label = "Password",
                hint = "Enter your password",
                onValueChanged = { viewModel.setPassword(it) },
                isPassword = true,
                trailingIcon = R.drawable.close_eye,
                iconDescription = "User icon"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    viewModel.loginUser(context)
                    Log.d("SignInScreen", "token: $token")



                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = P300),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = email.isNotBlank() && (password.isNotBlank() || !isPassError)
            ) {
                Log.d("SignInScreen", "isPassError: $isPassError")
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
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "Sign Up",
                    color = Color(0xff871e35),
                    textDecoration = TextDecoration.Underline,
                    lineHeight = 8.12.em,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.clickable { navController.navigate(AppRoutes.SIGN_UP_ROUTE) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    //SignInScreen(SignInViewModel(UserSource()))
}
