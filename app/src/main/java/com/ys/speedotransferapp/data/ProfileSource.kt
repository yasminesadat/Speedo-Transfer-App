package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.ui_model.Profile

class ProfileSource {
    fun getProfile() = Profile(
        "Yasmine Elsadat", "100,200,300",
        listOf(), listOf()
    )
}