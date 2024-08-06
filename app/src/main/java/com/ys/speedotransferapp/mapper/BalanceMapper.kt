package com.ys.speedotransferapp.mapper

import com.ys.speedotransferapp.data_model.BalanceResponse

object BalanceMapper {
    // Convert data model to UI model
    fun mapToView(balanceResponse:BalanceResponse)= balanceResponse.balance.toString() +" LE"
}