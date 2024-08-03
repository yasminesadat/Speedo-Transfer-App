package com.ys.speedotransferapp.model

import androidx.annotation.DrawableRes

data class TransactionItem(
    val recipientName: String,
    val paymentMethod: String,
    val lastFourDigits: String,
    val dateTime: String,
    val status: String,
    @DrawableRes val paymentProcessorIcon: Int,
    val cardType: String,
    //details
    val cardHolderName: String,
    val amount: String,
    val currency: String,
    val id: String,
    val cvv: String
)
