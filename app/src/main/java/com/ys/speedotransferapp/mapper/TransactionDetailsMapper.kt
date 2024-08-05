package com.ys.speedotransferapp.mapper


import com.ys.speedotransferapp.data_model.TransactionDTO
import com.ys.speedotransferapp.ui_model.TransactionDetails
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency

object TransactionDetailsMapper {

    // Convert data model to UI model
    fun mapToView(transactionDTO: TransactionDTO) =
        TransactionDetails(
            reference = transactionDTO.id,
            senderName = transactionDTO.senderName,
            senderAccount = transactionDTO.senderAccountNumber,
            recipientName = transactionDTO.recipientName,
            recipientAccount = transactionDTO.recipientAccountNumber,
            dateTime = formatDate(transactionDTO.transactionTime),
            status = transactionDTO.status,
            paymentProcessor = transactionDTO.cardType,
            amount = formatAmount(transactionDTO.amount, transactionDTO.currency),
            currency = transactionDTO.currency
        )


    private fun formatAmount(amount: Double, currencyCode: String): String {
        val formattedAmount = NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance(currencyCode)
            maximumFractionDigits = 2
        }.format(amount)

        // handle EGP to LE and place at end of amount
        return if (formattedAmount.take(3) == "EGP") formattedAmount.drop(3) + " LE" else formattedAmount
    }

    private fun formatDate(dateTime: String): String {

        val inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")

        val localDateTime = LocalDateTime.parse(dateTime, inputFormatter)

        val today = LocalDate.now()

        return if (localDateTime.toLocalDate() == today) {
            "Today " + localDateTime.format(timeFormatter)
        } else {
            localDateTime.format(dateFormatter) + " " + localDateTime.format(timeFormatter)
        }
    }
}

