package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.ui_model.FavouriteItem

class FavouritesSource {
    //get from API and only last 4 digits of account
    fun getFavourites(): List<FavouriteItem> {
        val favourites = mutableListOf<FavouriteItem>().apply {
            add(
                FavouriteItem(
                    name = "Ahmed Fahmy",
                    accountNumber = "85621234"
                )
            )
            add(
                FavouriteItem(
                    name = "Hoda Mustafa",
                    accountNumber = "59865678"
                )
            )
            add(
                FavouriteItem(
                    name = "John Doe",
                    accountNumber = "542959101"
                )
            )
            add(
                FavouriteItem(
                    name = "Jane Smith",
                    accountNumber = "82811121"
                )
            )
            add(
                FavouriteItem(
                    name = "Alice Johnson",
                    accountNumber = "85643141"
                )
            )
            add(
                FavouriteItem(
                    name = "Bob Brown",
                    accountNumber = "85345161"
                )
            )
            add(
                FavouriteItem(
                    name = "Charlie Davis",
                    accountNumber = "85627181"
                )
            )
            add(
                FavouriteItem(
                    name = "Diana Evans",
                    accountNumber = "85659202"
                )
            )
            add(
                FavouriteItem(
                    name = "Eve Foster",
                    accountNumber = "52581222"
                )
            )
            add(
                FavouriteItem(
                    name = "Frank Green",
                    accountNumber = "85623242"
                )
            )

        }
        return favourites
    }
}