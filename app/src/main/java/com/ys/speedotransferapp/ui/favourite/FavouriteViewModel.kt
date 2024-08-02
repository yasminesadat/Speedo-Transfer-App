package com.ys.speedotransferapp.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.data.FavouriteItemsSource
import com.ys.speedotransferapp.model.FavouriteItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {

    private val _favourites = MutableStateFlow<List<FavouriteItem>>(emptyList())
    val favourites = _favourites.asStateFlow()

    private val _selectedFavourite: MutableStateFlow<FavouriteItem?> = MutableStateFlow(null)
    val selectedFavourite = _selectedFavourite.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet = _showBottomSheet.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _accountNumber = MutableStateFlow("")
    val accountNumber = _accountNumber.asStateFlow()

    init {
        viewModelScope.launch {
            _favourites.value = FavouriteItemsSource().getFavourites()
        }
    }

    fun deleteFavourite(favourite: FavouriteItem) {
        _favourites.value = _favourites.value.filter { it != favourite }
    }

    fun updateFavourite(oldFavourite: FavouriteItem, newFavourite: FavouriteItem) {
        newFavourite.accountNumber = "xxxx" + newFavourite.accountNumber.takeLast(4)
        _favourites.value = _favourites.value.map {
            if (it == oldFavourite) newFavourite else it
        }
    }

    fun showBottomSheet(show: Boolean) {
        _showBottomSheet.value = show
    }

    fun setSelectedFavourite(favourite: FavouriteItem?) {
        _selectedFavourite.value = favourite
    }

    fun setName(newName: String) {
        _name.value = newName
    }

    fun setAccountNumber(newAccountNumber: String) {
        _accountNumber.value = newAccountNumber.filter{input -> input.isDigit()}
    }
}
