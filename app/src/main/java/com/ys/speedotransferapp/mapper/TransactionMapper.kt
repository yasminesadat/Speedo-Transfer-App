package com.ys.speedotransferapp.mapper

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.data_model.TransactionDTO
import com.ys.speedotransferapp.ui_model.Transaction
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency

object TransactionMapper {

    // Convert data model to UI model
    private fun mapToView(transactionDTO: TransactionDTO) =
        Transaction(
            reference = transactionDTO.id,
            recipientName = transactionDTO.recipientName,
            recipientDigits = transactionDTO.recipientAccountNumber.takeLast(4),
            dateTime = formatDate(transactionDTO.transactionTime),
            status = transactionDTO.status,
            paymentProcessorIcon = R.drawable.mastercard,
            paymentProcessor = transactionDTO.cardType,
            amount = formatAmount(transactionDTO.amount, transactionDTO.currency)
        )

    // Convert list of data models to list of UI models
    fun mapToView(response: List<TransactionDTO>): List<Transaction> {
        return response.map { mapToView(it) }
    }

    private fun formatAmount(amount: Double, currencyCode: String): String {
        val formattedAmount = NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance(currencyCode)
            maximumFractionDigits = 2
        }.format(amount)

        // handle EGP to LE and place at end of amount
        return if (formattedAmount.take(3) == "EGP") formattedAmount.drop(3) + " LE" else formattedAmount
    }

    private fun formatDate(dateTime: String): String {
        // Formatter to parse the input date-time string
        val inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

        // Formatters for output
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")

        // Parse the input date-time string
        val localDateTime = LocalDateTime.parse(dateTime, inputFormatter)

        // Get today's date
        val today = LocalDate.now()

        // Format the date-time based on whether it is today or not
        return if (localDateTime.toLocalDate() == today) {
            "Today " + localDateTime.format(timeFormatter)
        } else {
            localDateTime.format(dateFormatter) + " " + localDateTime.format(timeFormatter)
        }
    }
}

