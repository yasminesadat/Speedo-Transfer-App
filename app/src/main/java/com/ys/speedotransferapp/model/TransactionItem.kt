package com.ys.speedotransferapp.model

import androidx.annotation.DrawableRes

data class TransactionItem(
    val recipientName: String,
    val paymentMethod: String,
    val recipientDigits: String,
    val dateTime: String,
    val status: String,
    @DrawableRes val paymentProcessorIcon: Int,
    val paymentProcessor: String,
    //details
    val senderName: String,
    val amount: String,
    val currency: String,
    val reference: Int,
    val senderDigits: String
    )
