package com.ys.speedotransferapp.mapper

import com.ys.speedotransferapp.data_model.NameData
import com.ys.speedotransferapp.ui_model.Profile

object ProfileMapper {
    // Convert data model to UI model
    fun mapToView(nameData: NameData) =
        Profile(
            name = nameData.firstName+" "+nameData.lastName,
            initials = nameData.firstName.take(1) + nameData.lastName.take(1)
        )
}