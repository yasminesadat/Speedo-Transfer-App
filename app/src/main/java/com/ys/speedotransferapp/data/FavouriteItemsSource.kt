package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.model.FavouriteItem

class FavouriteItemsSource {
    //get from API and only last 4 digits of account
    fun getFavourites():List<FavouriteItem>{
        val favourites = mutableListOf<FavouriteItem>().apply{
            add(
                FavouriteItem(name = "Ahmed Fahmy",
                accountNumber = "Account xxxx1234")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
            add(
                FavouriteItem(name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678")
            )
        }
        return favourites
    }
}