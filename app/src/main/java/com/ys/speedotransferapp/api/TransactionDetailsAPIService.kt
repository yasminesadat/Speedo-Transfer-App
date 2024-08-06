package com.ys.speedotransferapp.api


import com.ys.speedotransferapp.mock.TransactionInterceptor
import com.ys.speedotransferapp.constants.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TransactionDetailsAPIService {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(TransactionInterceptor()) // Add the interceptor
        .build()

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(client) //remove later
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable: TransactionDetailsAPICallable by lazy {
        retrofit.create(TransactionDetailsAPICallable::class.java)
    }

}