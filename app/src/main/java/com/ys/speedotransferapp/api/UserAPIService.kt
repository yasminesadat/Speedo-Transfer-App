package com.ys.speedotransferapp.api

import com.ys.speedotransferapp.constants.AppConstants.BASE_URL
import com.ys.speedotransferapp.mock.UserInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserAPIService {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(UserInterceptor()) // Add the interceptor
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client) //remove later
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable: UserAPICallable by lazy {
        retrofit.create(UserAPICallable::class.java)
    }

}