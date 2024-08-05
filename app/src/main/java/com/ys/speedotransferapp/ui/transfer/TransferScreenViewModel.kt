package com.ys.speedotransferapp.ui.transfer


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.data.CurrienciesSource
import com.ys.speedotransferapp.ui_model.Currencies
import com.ys.speedotransferapp.ui_model.TransferState
import com.ys.speedotransferapp.ui_model.TransferStep
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransferScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(TransferState())
    val state: StateFlow<TransferState> = _state.asStateFlow()


    private val _amount_sending = MutableStateFlow("")
    val amount_sending = _amount_sending.asStateFlow()


    private val _recName = MutableStateFlow("")
    val recName = _recName.asStateFlow()

    private val _recAccount = MutableStateFlow("")
    val recAccount = _recAccount.asStateFlow()


    private val _amountError = MutableStateFlow<String?>(null)
    val amountError: StateFlow<String?> get() = _amountError

    private val _recNameError = MutableStateFlow<String?>(null)
    val recNameError: StateFlow<String?> = _recNameError

    private val _recAccountError = MutableStateFlow<String?>(null)
    val recAccountError: StateFlow<String?> = _recAccountError

    private fun convertBalanceStringToDouble(balance: String): Double {
        return balance.replace(",", "").toDoubleOrNull() ?: 0.0
    }


    fun onAmountSendChange(amount: String) {
        _amount_sending.value = amount
    }

    fun validateAmountField(viewModel: HomeViewModel): Boolean {
        var isValid = true

        // Validate amount
        val amount = _amount_sending.value.toDoubleOrNull()
        val maxAmount = 5000.0
        val userBalance = viewModel.profile.balance // Replace with actual method to get the user's balance

        // Check if amount is null or empty
        if (amount == null || amount <= 0) {
            _amountError.value = "Amount cannot be empty or zero."
            isValid = false
        }
        // Check if amount exceeds the maximum limit
        else if (amount > maxAmount) {
            _amountError.value = "Invalid amount. Must be less than or equal to $maxAmount LE."
            isValid = false
        }
        // Check if amount is a valid number with one decimal place
        else if (!Regex("^[0-9]+(\\.[0-9]{1,1})?\$").matches(_amount_sending.value)) {
            _amountError.value = "Invalid amount format. Must be a number with up to one decimal point."
            isValid = false
        }
        // Check if amount exceeds the user's balance
        else if (amount > convertBalanceStringToDouble(userBalance)) {
            _amountError.value = "Amount exceeds your available balance."
            isValid = false
        } else {
            _amountError.value = null
        }

        // You can add additional validation for other fields here

        return isValid
    }

    fun validateFields(viewModel: HomeViewModel): Boolean {
        val isAmountFieldValid = validateAmountField(viewModel)
        var isValid = true
        // Validate amount

        // Validate recipient name
        if (_recName.value.isBlank()) {
            _recNameError.value = "Recipient name cannot be empty."
            isValid = false
        } else {
            _recNameError.value = null
        }

        // Validate recipient account
        if (_recAccount.value.isBlank()) {
            _recAccountError.value = "Recipient account cannot be empty."
            isValid = false
        } else {
            _recAccountError.value = null
        }

        return isValid && isAmountFieldValid



    fun onRecNameChange(newText: String) {
        _recName.value = newText
    }

    fun onRecAccountChange(newText: String) {
        _recAccount.value = newText
    }

    fun setAmount(amount: Double) {
        _state.update { it.copy(amount = amount, currentStep = TransferStep.CONFIRMATION) }
    }


    fun loadTransferDetails(context: Context) {
        val sharedPreferences = context.getSharedPreferences("transfer_data", Context.MODE_PRIVATE)
        val loadedAmount = sharedPreferences.getString("amount", "") ?: ""
        val loadedName = sharedPreferences.getString("recName", "") ?: ""
        val loadedAccount = sharedPreferences.getString("recAccount", "") ?: ""

        _amount_sending.value = loadedAmount
        _recName.value = loadedName
        _recAccount.value = loadedAccount

        Log.d("TransferViewModel", "Loaded: Amount=$loadedAmount, Name=$loadedName, Account=$loadedAccount")
    }
    fun clearTransferDetails(context: Context) {
        val editor = context.getSharedPreferences("transfer_data", Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
        Log.d("TransferViewModel", "Cleared all transfer details from SharedPreferences")
    }


    fun confirmTransfer() {
        _state.update { it.copy(currentStep = TransferStep.PAYMENT) }
        // Here you would typically call a suspend function to perform the transfer
        // For now, we'll just simulate a successful transfer
        viewModelScope.launch {
            delay(1000) // Simulate network call
            _state.update { it.copy(isTransferSuccessful = true) }
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNotification(title: String, text: String, context: Context) {
        var builder = NotificationCompat.Builder(context, "1").setSmallIcon(R.drawable.received)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
        NotificationManagerCompat.from(context).notify(99, builder.build())
    }

    fun goToNextStep() {
        _state.update {
            when (it.currentStep) {
                TransferStep.AMOUNT -> it.copy(currentStep = TransferStep.CONFIRMATION)
                TransferStep.CONFIRMATION -> it.copy(currentStep = TransferStep.PAYMENT)
                TransferStep.PAYMENT -> it // No next step, stay on PAYMENT
            }
        }
    }

    fun goToPreviousStep() {
        _state.update {
            it.copy(
                currentStep = when (it.currentStep) {
                    TransferStep.CONFIRMATION -> TransferStep.AMOUNT
                    TransferStep.PAYMENT -> TransferStep.CONFIRMATION
                    else -> it.currentStep
                }
            )
        }
    }



    fun resetTransfer() {
        _state.value = TransferState()
    }
}
