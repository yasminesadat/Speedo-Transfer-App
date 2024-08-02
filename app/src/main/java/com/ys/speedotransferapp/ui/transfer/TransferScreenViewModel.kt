package com.ys.speedotransferapp.ui.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.model.TransferState
import com.ys.speedotransferapp.model.TransferStep
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransferScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(TransferState())
    val state: StateFlow<TransferState> = _state.asStateFlow()

    fun setAmount(amount: Double) {
        _state.update { it.copy(amount = amount, currentStep = TransferStep.CONFIRMATION) }
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
            it.copy(currentStep = when (it.currentStep) {
                TransferStep.CONFIRMATION -> TransferStep.AMOUNT
                TransferStep.PAYMENT -> TransferStep.CONFIRMATION
                else -> it.currentStep
            })
        }
    }

    fun resetTransfer() {
        _state.value = TransferState()
    }
}