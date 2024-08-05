package com.ys.speedotransferapp.ui.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.api.TransactionAPIService
import com.ys.speedotransferapp.constants.AppConstants.FAILED
import com.ys.speedotransferapp.paging.TransactionsPagingSource
import com.ys.speedotransferapp.ui.theme.Green
import com.ys.speedotransferapp.ui.theme.LightGreen
import com.ys.speedotransferapp.ui.theme.LightRed
import com.ys.speedotransferapp.ui.theme.Red
import com.ys.speedotransferapp.ui_model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow

class TransactionsViewModel : ViewModel() {

    private var switchIcon = false

     val transactions: Flow<PagingData<Transaction>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { TransactionsPagingSource(TransactionAPIService) }
    ).flow.cachedIn(viewModelScope)
    //val transactions = _transactions.asStateFlow()

   // private val mapper = TransactionMapper()

//    init {
//        getTransactions()
//    }
//
//    private fun getTransactions() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val fetchedTransactions =
//                    TransactionAPIService.callable.getTransactions(1, 3, "Bearer token")
//                _transactions.update { mapper.mapToView(fetchedTransactions) }
//            } catch (e: Exception) {
//                Log.e("TAG", "Exception occurred", e)
//            }
//        }
//    }

    fun getIcon(): Int {
        switchIcon = !switchIcon
        return if (switchIcon) R.drawable.bank else R.drawable.card2
    }

    fun getTextColor(status: String) = if (status == FAILED) Red else Green

    fun getBackgroundColor(status: String) = if (status == FAILED) LightRed else LightGreen

    fun getCardDetails(transaction: Transaction) = transaction.paymentProcessor + " . " +
            transaction.recipientDigits.takeLast(4)
}