package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.model.OptionItem

class OptionsSource {
    fun getOptions(): List<OptionItem>{
        val options = mutableListOf<OptionItem>().apply {
            add(OptionItem(R.drawable.website, "Transfer from Website"))
            add(OptionItem(R.drawable.favorite, "Favourites"))
            add(OptionItem(R.drawable.profile, "Profile"))
            add(OptionItem(R.drawable.help, "Help"))
            add(OptionItem(R.drawable.logout, "Logout", isLast = true))
        }
        return options
    }
}