package com.ys.speedotransferapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

    private var _selectedItemIndex = MutableStateFlow(0)
    val selectedItemIndex = _selectedItemIndex.asStateFlow()

    fun setItemIndex(newIndex: Int){
        _selectedItemIndex.value = newIndex
    }
}