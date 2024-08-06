package com.ys.speedotransferapp.constants

object AppConstants {
    const val NUMBER = 1234
    const val EMAIL = "help@speedo.com"
    const val FAILED = "Failed"

    const val BASE_URL = "https://speedotransfer1-506cf0850222.herokuapp.com"

    const val TRANSACTIONS_ENDPOINT = "/api/transactions"
    const val TRANSACTION_DETAILS_ENDPOINT = "/api/transactions/{transactionId}"
    const val REGISTER_ENDPOINT = "/api/auth/register"
    const val LOGIN_ENDPOINT = "/api/auth/login"
    const val LOGOUT_ENDPOINT = "/api/auth/logout"
    const val BALANCE_ENDPOINT = "/api/balance"
}