package com.ys.speedotransferapp.ui.favourite

import androidx.lifecycle.ViewModel
import com.ys.speedotransferapp.data.FavouriteItemsSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouriteViewModel: ViewModel() {
    private var _favourites = MutableStateFlow(FavouriteItemsSource().getFavourites())

    val favourites = _favourites.asStateFlow()
}