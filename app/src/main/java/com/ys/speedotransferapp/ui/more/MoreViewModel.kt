package com.ys.speedotransferapp.ui.more

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MoreViewModel : ViewModel() {

    var showHelpBottomSheet by mutableStateOf(false)
    private set

    fun showHelpBottomSheet(show: Boolean) {
       showHelpBottomSheet = show
    }
}
