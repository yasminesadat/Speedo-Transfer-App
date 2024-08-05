package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.ui_model.TransactionDetails

class TransactionDetailsSource {
    fun getTransaction() = TransactionDetails(
        recipientName = "Peter Parker",
        recipientAccount = "Account xxxx5678",
        dateTime = "15 May 14:15",
        status = "Failed",
        paymentProcessor = "Mastercard",
        senderName = "Mary Jane",
        amount = "175.00",
        currency = "USD",
        reference = 234567,
        senderAccount = "Account xxxx1234"
    )
}