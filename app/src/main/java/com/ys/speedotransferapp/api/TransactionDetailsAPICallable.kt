package com.ys.speedotransferapp.api

import com.ys.speedotransferapp.constants.AppConstants.TRANSACTION_DETAILS_ENDPOINT
import com.ys.speedotransferapp.data_model.TransactionDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TransactionDetailsAPICallable {

        @GET(TRANSACTION_DETAILS_ENDPOINT)
        suspend fun getTransactionDetails(
            @Path("transactionId") transactionId: Long,
            @Header("Authorization") authorization: String
        ): TransactionDTO

}