package com.ys.speedotransferapp.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.api.TransactionDetailsAPICallable
import com.ys.speedotransferapp.api.TransactionDetailsAPIService
import com.ys.speedotransferapp.constants.AppConstants.BEARER
import com.ys.speedotransferapp.constants.AppConstants.FAILED
import com.ys.speedotransferapp.mapper.TransactionDetailsMapper
import com.ys.speedotransferapp.ui_model.TransactionDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val transactionID: Long,
    private val apiService: TransactionDetailsAPICallable = TransactionDetailsAPIService.callable,
    private val token: String = "" //placeholder
) : ViewModel() {

    private val _transaction = MutableStateFlow<TransactionDetails?>(null)

    val transaction = _transaction.asStateFlow()

    init {
        fetchTransactionDetails()
    }

    private fun fetchTransactionDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val transactionDTO = apiService.getTransactionDetails(transactionID, BEARER+token)
                val transactionDetails = TransactionDetailsMapper.mapToView(transactionDTO)
                _transaction.value = transactionDetails
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
