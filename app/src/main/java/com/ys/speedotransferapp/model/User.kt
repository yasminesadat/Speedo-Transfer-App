package com.ys.speedotransferapp.model

import com.ys.speedotransferapp.ui_model.TransactionItem

data class User(

    val name: String,
    val email: String,
    val password: String,
    val country: String,
    val birthDate: String,
    val favourites: List<String>,
    val transactions: List<TransactionItem>
) {
}