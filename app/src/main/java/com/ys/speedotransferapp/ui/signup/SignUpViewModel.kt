package com.ys.speedotransferapp.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {
    private val _fullName = MutableStateFlow("")
    val fullName = _fullName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _country = MutableStateFlow("")
    val country = _country.asStateFlow()

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth = _dateOfBirth.asStateFlow()

    fun signUp() {
        viewModelScope.launch(Dispatchers.IO) {
            // Call the API to sign up the user
        }
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




}