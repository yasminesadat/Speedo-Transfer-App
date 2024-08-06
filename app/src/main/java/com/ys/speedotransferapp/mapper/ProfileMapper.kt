package com.ys.speedotransferapp.mapper

import com.ys.speedotransferapp.data_model.RegisterResponse
import com.ys.speedotransferapp.ui_model.Profile

class ProfileMapper {
    fun mapRegisterResponseToProfile(registerResponse: RegisterResponse, fullName: String): Profile {
        return Profile(
            firstAndSurname = fullName,
            balance = "0",  // Assuming initial balance is 0 as it's not provided in the response
            favourites = emptyList(),
            cards = emptyList(),
        )
    }
}
