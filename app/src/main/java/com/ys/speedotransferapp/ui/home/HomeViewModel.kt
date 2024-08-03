package com.ys.speedotransferapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.data.ProfileSource
import com.ys.speedotransferapp.data.TransactionsSource
import com.ys.speedotransferapp.model.TransactionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<TransactionItem>>(emptyList())
    val transactions: StateFlow<List<TransactionItem>> = _transactions.asStateFlow()

    val profile =ProfileSource().getProfile()

    init {
        viewModelScope.launch {
            _transactions.value = TransactionsSource().getTransactions()
        }
    }

    fun getInitials(): String{
        val names = profile.firstAndSurname.split(" ")
        val initials = names.map { it.first() }.joinToString("")
        return initials
    }
}