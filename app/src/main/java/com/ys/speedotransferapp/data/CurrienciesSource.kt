package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.model.Currencies

class CurrienciesSource {
    fun get (): List<Currencies> {
        val currencies = mutableListOf<Currencies>().apply {
            add(
                Currencies(
                    curr_icon = R.drawable.united_states,
                    curr_code = "USD",
                    curr_name = "US Dollar"
                )
            )
            add(
                Currencies(
                    curr_icon = R.drawable.egypt,
                    curr_code = "EGP",
                    curr_name = "Egyptian Pound"
                )
            )

        }
        return currencies
    }
}