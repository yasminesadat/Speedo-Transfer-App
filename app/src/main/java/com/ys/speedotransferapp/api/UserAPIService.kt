package com.ys.speedotransferapp.api

import com.ys.speedotransferapp.constants.AppConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserAPIService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable: UserAPICallable by lazy {
        retrofit.create(UserAPICallable::class.java)
    }

}