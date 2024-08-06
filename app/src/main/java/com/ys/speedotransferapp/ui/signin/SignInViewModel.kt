package com.ys.speedotransferapp.ui.signin

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.api.UserAPIService
import com.ys.speedotransferapp.data_model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult = _loginResult.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun loginUser(context: Context) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            Log.d("SignInViewModel", "Starting login request")
            val loginRequest = LoginRequest(
                email = email.value,
                password = password.value
            )

            val loginResponse = UserAPIService.callable.loginUser(loginRequest)
            if (loginResponse.status == "ACCEPTED") {
                val token = loginResponse.token ?: throw Exception("Token is null")
                Log.d("SignInViewModel", "Login successful, token: $token")
                _loginResult.value = Result.success(token)
                saveToken(context, token)
            } else {
                Log.e("SignInViewModel", "Login failed: ${loginResponse.message}")
                throw Exception(loginResponse.message)
            }
        } catch (e: Exception) {
            Log.e("SignInViewModel", "Login error occurred", e)
            _loginResult.value = Result.failure(e)
        } finally {
            Log.d("SignInViewModel", "Login request completed")
        }
    }
}

    private fun saveToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("auth_data", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("token", token)
            apply()
        }
    }

    fun loadToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("auth_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }

    //load auth data
    fun loadSignInDetails(context: Context) {
        val sharedPreferences = context.getSharedPreferences("sign_up_data", Context.MODE_PRIVATE)
        val loadedEmail = sharedPreferences.getString("email", "") ?: ""
        val loadedPassword = sharedPreferences.getString("password", "") ?: ""

        _email.value = loadedEmail
        _password.value = loadedPassword

        Log.d("SignInViewModel", "Loaded: Email=$loadedEmail, Password=$loadedPassword")
    }
}
