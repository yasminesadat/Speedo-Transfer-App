package com.ys.speedotransferapp.ui.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.data.TransactionsSource
import com.ys.speedotransferapp.model.TransactionItem
import com.ys.speedotransferapp.ui.theme.Green
import com.ys.speedotransferapp.ui.theme.LightGreen
import com.ys.speedotransferapp.ui.theme.LightRed
import com.ys.speedotransferapp.ui.theme.Red
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsViewModel: ViewModel() {

    private var switchIcon = false

    private val _transactions = MutableStateFlow<List<TransactionItem>>(emptyList())
    val transactions = _transactions.asStateFlow()

    init {
        viewModelScope.launch {
            _transactions.value = TransactionsSource().getTransactions()
        }
    }

    fun getIcon(): Int {
        switchIcon = !switchIcon
        return if(switchIcon) R.drawable.bank else R.drawable.card2
    }

    fun getTextColor(status: String) = if (status == "Failed") Red else Green

    fun getBackgroundColor(status: String) = if (status == "Failed") LightRed else LightGreen
}