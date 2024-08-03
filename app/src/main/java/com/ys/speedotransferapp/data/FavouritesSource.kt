package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.ui_model.FavouriteItem

class FavouritesSource {
    //get from API and only last 4 digits of account
    fun getFavourites(): List<FavouriteItem> {
        val favourites = mutableListOf<FavouriteItem>().apply {
            add(
                FavouriteItem(
                    name = "Ahmed Fahmy",
                    accountNumber = "Account xxxx1234"
                )
            )
            add(
                FavouriteItem(
                    name = "Hoda Mustafa",
                    accountNumber = "Account xxxx5678"
                )
            )
            add(
                FavouriteItem(
                    name = "John Doe",
                    accountNumber = "Account xxxx9101"
                )
            )
            add(
                FavouriteItem(
                    name = "Jane Smith",
                    accountNumber = "Account xxxx1121"
                )
            )
            add(
                FavouriteItem(
                    name = "Alice Johnson",
                    accountNumber = "Account xxxx3141"
                )
            )
            add(
                FavouriteItem(
                    name = "Bob Brown",
                    accountNumber = "Account xxxx5161"
                )
            )
            add(
                FavouriteItem(
                    name = "Charlie Davis",
                    accountNumber = "Account xxxx7181"
                )
            )
            add(
                FavouriteItem(
                    name = "Diana Evans",
                    accountNumber = "Account xxxx9202"
                )
            )
            add(
                FavouriteItem(
                    name = "Eve Foster",
                    accountNumber = "Account xxxx1222"
                )
            )
            add(
                FavouriteItem(
                    name = "Frank Green",
                    accountNumber = "Account xxxx3242"
                )
            )

        }
        return favourites
    }
}