package com.ys.speedotransferapp.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var selectedItemIndex by  mutableStateOf(0)
        private set

    fun setItemIndex(newIndex: Int) {
        selectedItemIndex = newIndex
    }
}