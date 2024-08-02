package com.ys.speedotransferapp.ui.more

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MoreViewModel : ViewModel() {

    private var _showHelpBottomSheet = MutableStateFlow(false)
    val showHelpBottomSheet = _showHelpBottomSheet.asStateFlow()

    fun showHelpBottomSheet(show: Boolean) {
        _showHelpBottomSheet.value = show
    }
}
