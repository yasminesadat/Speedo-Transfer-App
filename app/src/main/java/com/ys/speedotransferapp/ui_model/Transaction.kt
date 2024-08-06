package com.ys.speedotransferapp.ui_model

import androidx.annotation.DrawableRes

data class Transaction(
    val reference: Long,
    val recipientName: String,
    val recipientDigits: String,
    val dateTime: String,
    val status: String,
    @DrawableRes val paymentProcessorIcon: Int,
    val paymentProcessor: String,
    val amount: String
)