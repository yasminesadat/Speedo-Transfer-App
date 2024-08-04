package com.ys.speedotransferapp.ui.transfer

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ys.speedotransferapp.data.CurrienciesSource
import com.ys.speedotransferapp.model.Currencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CurrenciesViewModel : ViewModel() {
    private val _selectedOption = MutableStateFlow(Currencies(0, "", ""))
    val selectedOption: StateFlow<Currencies> = _selectedOption

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded: StateFlow<Boolean> = _isExpanded

    val options = CurrienciesSource().get()
    init {
        _selectedOption.value = options[0]
    }
    fun onDropdownClicked() {
        _isExpanded.value = !_isExpanded.value
    }

    fun onOptionSelected(option: Currencies) {
        _selectedOption.value = option
        _isExpanded.value = false
    }

    fun loadSelectedCurrencyOption(context: Context, prefKey: String) {
        val sharedPreferences = context.getSharedPreferences("currency_data", Context.MODE_PRIVATE)
        val index = sharedPreferences.getInt(prefKey, 0)
        if (index in options.indices) {
            _selectedOption.value = options[index]
        }
    }
    fun clearCurrencyDetails(context: Context) {
        val sharedPreferences = context.getSharedPreferences("currency_data", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}
