package com.ys.speedotransferapp.ui.transfer

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ys.speedotransferapp.data.CurrencyExchangeRateSource
import com.ys.speedotransferapp.data.CurrienciesSource
import com.ys.speedotransferapp.ui_model.Currencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CurrenciesViewModel : ViewModel() {
    private val _selectedOption = MutableStateFlow(Currencies(0, "", ""))
    val selectedOption: StateFlow<Currencies> = _selectedOption

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded: StateFlow<Boolean> = _isExpanded

    private val _selectedOptionsList = MutableStateFlow<List<Currencies>>(listOf(Currencies(0, "", ""), Currencies(0, "", "")))
    val selectedOptionsList: StateFlow<List<Currencies>> = _selectedOptionsList

    private val currencyExchangeRateSource = CurrencyExchangeRateSource()
    val options = CurrienciesSource().get()

    init {
        _selectedOption.value = options[0]
    }

    fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        return currencyExchangeRateSource.getExchangeRate(fromCurrency, toCurrency)
    }

    fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val rate = getExchangeRate(fromCurrency, toCurrency)
        return amount * rate
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

    fun loadSelectedCurrencyOptionsList(context: Context, prefKeys: List<String>) {
        val sharedPreferences = context.getSharedPreferences("currency_data", Context.MODE_PRIVATE)
        val selectedOptions = prefKeys.mapNotNull { key ->
            val index = sharedPreferences.getInt(key, -1)
            if (index in options.indices) options[index] else null
        }
        _selectedOptionsList.value = selectedOptions
    }

    fun clearCurrencyDetails(context: Context) {
        val sharedPreferences = context.getSharedPreferences("currency_data", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}