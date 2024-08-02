package com.ys.speedotransferapp.ui.favourite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private var selectedFavourite by mutableStateOf<FavouriteItem?>(null)
        private set

    var showBottomSheet by mutableStateOf(false)
        private set

    var name by mutableStateOf("")
        private set

    var accountNumber by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            _favourites.value = FavouriteItemsSource().getFavourites()
        }
    }

    fun deleteFavourite(favourite: FavouriteItem) {
        _favourites.value = _favourites.value.filter { it != favourite }
    }

    fun updateFavourite() {
        accountNumber = "xxxx" + accountNumber.takeLast(4)
        val updatedFavourite = FavouriteItem(name, accountNumber)
        _favourites.value = _favourites.value.map {
            if (it == selectedFavourite) updatedFavourite else it
        }
    }

    fun showBottomSheet(show: Boolean) {
        showBottomSheet = show
    }

    fun updateSelectedFavourite(favourite: FavouriteItem) {
        selectedFavourite = favourite
    }

    fun updateName(newName: String) {
        name = newName
    }

    fun updateAccountNumber(newAccountNumber: String) {
        accountNumber = newAccountNumber.filter { input -> input.isDigit() }
    }
}
