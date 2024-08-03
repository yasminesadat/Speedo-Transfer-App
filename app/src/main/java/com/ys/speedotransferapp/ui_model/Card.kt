package com.ys.speedotransferapp.ui_model

data class Card(
    val cardHolderName: String,
    val cardNumber: Int,
    val expiryDate: String,
    val cardCVV: Int,
    val currency: String
)