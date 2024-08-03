package com.ys.speedotransferapp.ui.common

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CommonComposableViewModel : ViewModel() {
    var passwordVisible by mutableStateOf(false)
    var errorMessages = mutableStateMapOf<String, String?>()
    private var lastInputTimes = mutableStateMapOf<String, Long>()

    fun onValueChanged(fieldId: String, newValue: String, isPassword: Boolean) {
        lastInputTimes[fieldId] = System.currentTimeMillis()
        if (newValue.isEmpty()) {
            errorMessages[fieldId] = null
        } else {
            if (isPassword && fieldId == "password") {
                validatePasswordAfterDelay(fieldId, newValue)
            } else {
                // Clear error for non-password fields
                errorMessages[fieldId] = null
            }
        }
    }

    private fun validatePasswordAfterDelay(fieldId: String, password: String) {
        viewModelScope.launch {
            delay(700)
            if (System.currentTimeMillis() - (lastInputTimes[fieldId] ?: 0) >= 700) {
                errorMessages[fieldId] = validatePassword(password)
            }
        }
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    private fun validatePassword(password: String): String? {
        if (password.length < 6) {
            return "Password must be at least 6 characters long"
        }
        if (!password.any { it.isUpperCase() }) {
            return "Password must contain at least one uppercase letter"
        }
        if (!password.any { it.isLowerCase() }) {
            return "Password must contain at least one lowercase letter"
        }
        if (!password.any { it in "!@#$%^&*()_+-=[]{}|;:,.<>?" }) {
            return "Password must contain at least one special character"
        }
        return null // Password is valid
    }
}