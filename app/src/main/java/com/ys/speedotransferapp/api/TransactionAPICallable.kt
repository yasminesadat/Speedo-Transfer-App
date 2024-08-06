package com.ys.speedotransferapp.api

import com.ys.speedotransferapp.constants.AppConstants.TRANSACTION_DETAILS_ENDPOINT
import com.ys.speedotransferapp.constants.AppConstants.TRANSACTIONS_ENDPOINT
import com.ys.speedotransferapp.data_model.TransactionDTO
import com.ys.speedotransferapp.data_model.TransactionResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionAPICallable {

    @GET(TRANSACTIONS_ENDPOINT)
    suspend fun getTransactions(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") authorization: String
    ): TransactionResponse

    @GET(TRANSACTION_DETAILS_ENDPOINT)
    suspend fun getTransactionDetails(
        @Path("transactionId") transactionId: Long,
        @Header("Authorization") authorization: String
    ): TransactionDTO

}