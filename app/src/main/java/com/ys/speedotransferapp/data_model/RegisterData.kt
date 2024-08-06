package com.ys.speedotransferapp.data_model


data class RegisterRequest(
    val userName: String,
    val email: String,
    val password: String,
    val country: String,
    val dateOfBirth: String
)


data class RegisterResponse(
    val id: Int,
)