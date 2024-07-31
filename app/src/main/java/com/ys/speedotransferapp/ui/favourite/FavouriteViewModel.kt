package com.ys.speedotransferapp.ui.favourite

import androidx.lifecycle.ViewModel
import com.ys.speedotransferapp.data.FavouriteItemsSource
import com.ys.speedotransferapp.model.FavouriteItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouriteViewModel : ViewModel() {

    private var _favourites = MutableStateFlow(FavouriteItemsSource().getFavourites())
    val favourites = _favourites.asStateFlow()

    private var _selectedFavourite: MutableStateFlow<FavouriteItem?> = MutableStateFlow(null)
    val selectedFavourite = _selectedFavourite.asStateFlow()

    private var _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet = _showBottomSheet.asStateFlow()

    fun deleteFavourite(favourite: FavouriteItem) {
        _favourites.value = _favourites.value.filter { it != favourite }
    }

    fun updateFavourite(oldFavourite: FavouriteItem, newFavourite: FavouriteItem) {
       newFavourite.accountNumber="xxxx"+newFavourite.accountNumber.takeLast(4)
        _favourites.value = _favourites.value.map {
            if (it == oldFavourite) newFavourite else it
        }
    }

    fun showBottomSheet(show: Boolean) {
        _showBottomSheet.value = show
    }

    fun setSelectedFavorite(favourite: FavouriteItem){
        _selectedFavourite.value=favourite
    }

}