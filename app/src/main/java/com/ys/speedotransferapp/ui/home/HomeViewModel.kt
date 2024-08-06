package com.ys.speedotransferapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.api.TransactionAPICallable
import com.ys.speedotransferapp.api.TransactionAPIService
import com.ys.speedotransferapp.api.UserAPICallable
import com.ys.speedotransferapp.api.UserAPIService
import com.ys.speedotransferapp.constants.AppConstants.BEARER
import com.ys.speedotransferapp.data.ServicesSource
import com.ys.speedotransferapp.mapper.BalanceMapper
import com.ys.speedotransferapp.mapper.ProfileMapper
import com.ys.speedotransferapp.mapper.TransactionMapper
import com.ys.speedotransferapp.ui_model.Profile
import com.ys.speedotransferapp.ui_model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val transactionApiService: TransactionAPICallable = TransactionAPIService.callable,
    private val userApiService: UserAPICallable = UserAPIService.callable,
    private val token: String = "" //placeholder
) : ViewModel() {
    val services = ServicesSource().getServices()

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    private val _userProfile = MutableStateFlow<Profile?>(null)
    val userProfile: StateFlow<Profile?> = _userProfile.asStateFlow()

    private val _balance = MutableStateFlow<String?>(null)
    val balance: StateFlow<String?> = _balance.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Fetch user profile
                val userProfileResponse = userApiService.getName(BEARER + token)
                _userProfile.value = ProfileMapper.mapToView(userProfileResponse)

                // Fetch balance
                val balanceResponse = userApiService.getBalance(BEARER + token)
                _balance.value = BalanceMapper.mapToView(balanceResponse)

                // Fetch latest 3 transactions
                val transactionsResponse =
                    transactionApiService.getTransactions(1, 3, BEARER + token)
                _transactions.value = TransactionMapper.mapToView(transactionsResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}