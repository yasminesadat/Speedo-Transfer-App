package com.ys.speedotransferapp.data_model

data class TransactionDTO(
    val id: Long,
    val senderAccountNumber: String,
    val recipientAccountNumber: String,
    val senderName: String,
    val recipientName: String,
    val amount: Double,
    val transactionTime: String,
    val currency: String,
    val cardType: String,
    val status: String
)
