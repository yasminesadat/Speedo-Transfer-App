package com.ys.speedotransferapp.ui.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.ys.speedotransferapp.data.UserSource

class SignInViewModel(private val userSource: UserSource): ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isUserValid = MutableStateFlow(false)
    val isUserValid = _isUserValid.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun verifyUser(): Boolean {
        val users = userSource.getUsers()
        return users.any { it.email == _email.value && it.password == _password.value }
    }
}