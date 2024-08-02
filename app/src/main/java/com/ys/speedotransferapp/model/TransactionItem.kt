package com.ys.speedotransferapp.model

data class TransactionItem(
    val recipientName: String,
    val paymentMethod: String,
    val lastFourDigits: String,
    val dateTime: String,
    val status: String,
    //details
    val cardHolderName: String,
    val amount: String,
    val currency: String,
    val id: String,
    val cvv: String
)
