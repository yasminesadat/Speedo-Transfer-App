package com.ys.speedotransferapp.model

data class Card(
    val cardHolderName: String,
    val cardNumber: Int,
    val expiryDate: String,
    val cardCVV: Int,
    val currency: String
)