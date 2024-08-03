package com.ys.speedotransferapp.model

data class Profile(
    val firstAndSurname: String,
    val balance: String,
    val cards: List<Card>,
    val favourites: List<FavouriteItem>
)