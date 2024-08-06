package com.ys.speedotransferapp.ui_model

import androidx.annotation.DrawableRes
import java.util.Currency

data class TransactionDetails(
    val reference: Long,
    val recipientName: String,
    val recipientAccount: String,
    val dateTime: String,
    val status: String,
    val paymentProcessor: String,
    val amount: String,
    val currency: String,
    //details
    val senderName: String,
    val senderAccount: String,
    val header: String,
    @DrawableRes val smallIcon: Int,
    @DrawableRes val largeIcon: Int,
    )
