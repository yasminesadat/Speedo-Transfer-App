package com.ys.speedotransferapp.api

import com.ys.speedotransferapp.constants.AppConstants.BALANCE_ENDPOINT
import com.ys.speedotransferapp.constants.AppConstants.LOGIN_ENDPOINT
import com.ys.speedotransferapp.constants.AppConstants.NAME_ENDPOINT
import com.ys.speedotransferapp.constants.AppConstants.REGISTER_ENDPOINT
import com.ys.speedotransferapp.data_model.BalanceResponse
import com.ys.speedotransferapp.data_model.LoginRequest
import com.ys.speedotransferapp.data_model.LoginResponse
import com.ys.speedotransferapp.data_model.LogoutRequest
import com.ys.speedotransferapp.data_model.LogoutResponse
import com.ys.speedotransferapp.data_model.NameData
import com.ys.speedotransferapp.data_model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPICallable {

    @POST(REGISTER_ENDPOINT)
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Long

    @POST(LOGIN_ENDPOINT)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST(LOGIN_ENDPOINT)
    suspend fun logoutUser(
        @Body logoutRequest: LogoutRequest
    ): LogoutResponse

    @GET(BALANCE_ENDPOINT)
    suspend fun getBalance(
        @Header("Authorization") token: String
    ): BalanceResponse

    @GET(NAME_ENDPOINT)
    suspend fun getName(
        @Header("Authorization") token: String
    ): NameData
}
