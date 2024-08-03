package com.ys.speedotransferapp.ui_model

data class TransactionDetails(
    val recipientName: String,
    val recipientAccount: String,
    val dateTime: String,
    val status: String,
    val paymentProcessor: String,
    val amount: String,
    //details
    val senderName: String,
    val reference: String,
    val senderAccount: String,
    val currency: String
    )
