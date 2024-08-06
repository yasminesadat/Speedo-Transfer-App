package com.ys.speedotransferapp.ui.signup

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.api.UserAPIService
import com.ys.speedotransferapp.data_model.RegisterRequest
import com.ys.speedotransferapp.data_model.RegisterResponse
import com.ys.speedotransferapp.data_model.LoginRequest
import com.ys.speedotransferapp.data_model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.Calendar

class SignUpViewModel : ViewModel() {
    private val _fullName = MutableStateFlow("")
    val fullName: StateFlow<String> = _fullName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _country = MutableStateFlow("")
    val country = _country.asStateFlow()

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth = _dateOfBirth.asStateFlow()

    private val _selectedDate = MutableStateFlow("")
    val selectedDate = _selectedDate.asStateFlow()

    private val _signUpResult = MutableStateFlow<Result<Long>?>(null)
    val signUpResult = _signUpResult.asStateFlow()

    private val _loginResult = MutableStateFlow<Result<String?>?>(null)
    val loginResult = _loginResult.asStateFlow()



    fun signUp(
        context: Context,
        fullName: String,
        email: String,
        password: String,
        country: String,
        dateOfBirth: String
    ):Boolean {
        var isSignUpSuccessful = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("SignUpViewModel", "name: $fullName, email: $email, password: $password, country: $country, dateOfBirth: $dateOfBirth")
                val registerRequest = RegisterRequest(
                    userName = fullName,
                    email = email,
                    password = password,
                    country = country,
                    dateOfBirth = dateOfBirth
                )
                val registerResponse = UserAPIService.callable.registerUser(registerRequest)
                _signUpResult.value = Result.success(registerResponse)

                // Login after successful sign up
                val loginRequest = LoginRequest(email = email, password = password)
                val loginResponse = UserAPIService.callable.loginUser(loginRequest)
                _loginResult.value = Result.success(loginResponse.token)

                // Store token
                saveToken(context, loginResponse.token)
                isSignUpSuccessful = true
                Log.d("SignUpViewModel", "Sign up successful $registerResponse and login successful with token ${loginResponse.token}")
            } catch (e: IOException) {
                Log.e("SignUpViewModel", "Network error occurred", e)
                _signUpResult.value = Result.failure(e)
                _loginResult.value = Result.failure(e)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("SignUpViewModel", "HTTP error occurred: $errorBody", e)
                _signUpResult.value = Result.failure(e)
                _loginResult.value = Result.failure(e)
            } catch (e: Exception) {
                Log.e("SignUpViewModel", "Unexpected error occurred", e)
                _signUpResult.value = Result.failure(e)
                _loginResult.value = Result.failure(e)
            }
        }
        return isSignUpSuccessful
    }

    fun setFullName(fullName: String) {
        _fullName.value = fullName
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setCountry(country: String) {
        _country.value = country
    }

    fun setDateOfBirth(dateOfBirth: String) {
        _dateOfBirth.value = dateOfBirth
    }

    fun showDatePicker(context: Context) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            0,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                _selectedDate.value = formattedDate
                setDateOfBirth(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    fun saveSignUpData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("sign_up_data", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("fullName", fullName.value)
            putString("email", email.value)
            putString("password", password.value)
            apply()
        }
    }

    fun loadSignUpData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("sign_up_data", Context.MODE_PRIVATE)
        _fullName.value = sharedPreferences.getString("fullName", "") ?: ""
        _email.value = sharedPreferences.getString("email", "") ?: ""
        _password.value = sharedPreferences.getString("password", "") ?: ""
    }

    private fun saveToken(context: Context, token: String?) {
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
}
