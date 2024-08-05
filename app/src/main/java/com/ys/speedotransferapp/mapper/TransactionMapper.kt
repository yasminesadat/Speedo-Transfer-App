package com.ys.speedotransferapp.mapper

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.data_model.TransactionDTO
import com.ys.speedotransferapp.data_model.TransactionResponse
import com.ys.speedotransferapp.ui_model.Transaction
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency

class TransactionMapper {

    // Convert data model to UI model
    private fun mapToView(transactionDTO: TransactionDTO): Transaction {
        return Transaction(
          reference = transactionDTO.id,
            recipientName = transactionDTO.recipientName,
            recipientDigits = transactionDTO.recipientAccountNumber,
            dateTime = formatDate(transactionDTO.transactionTime),
            status = transactionDTO.status,
            paymentProcessorIcon = R.drawable.mastercard,
            paymentProcessor = transactionDTO.cardType,
            amount = formatAmount(transactionDTO.amount, transactionDTO.currency)
        )
    }

    // Convert list of data models to list of UI models
    fun mapToView(response: TransactionResponse): List<Transaction> {
        return response.transactions.map { mapToView(it) }
    }

    private fun formatAmount(amount: Double, currencyCode: String): String {
        val currencySymbol = getCurrencySymbol(currencyCode)
        val formattedAmount = NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance(currencyCode)
            maximumFractionDigits = 2 // Adjust this as needed
        }.format(amount)

        // Replace default symbol with specific currency symbol if default symbol is not null
        return formattedAmount.replace(
            NumberFormat.getCurrencyInstance().currency?.symbol ?: "",
            currencySymbol
        )
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

    private fun getCurrencySymbol(currencyCode: String): String {
        return when (currencyCode) {
            "USD" -> "$"
            "EUR" -> "€"
            "EGP" -> "LE"
            else -> ""
        }
    }
}

