package com.ys.speedotransferapp.api

import com.ys.speedotransferapp.constants.AppConstants.TRANSACTIONS_ENDPOINT
import com.ys.speedotransferapp.data_model.TransactionDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TransactionAPICallable {

    @GET(TRANSACTIONS_ENDPOINT)
    suspend fun getTransactions(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") authorization: String
    ): List<TransactionDTO>

}