package com.ys.speedotransferapp.model

enum class TransferStep {
    AMOUNT, CONFIRMATION, PAYMENT
}

data class TransferState(
    val currentStep: TransferStep = TransferStep.AMOUNT,
    val amount: Double = 0.0,
    val fromAccount: String = "",
    val toAccount: String = "",
    val isTransferSuccessful: Boolean = false
)