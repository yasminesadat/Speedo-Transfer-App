package com.ys.speedotransferapp.mapper


import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.constants.AppConstants.FAILED
import com.ys.speedotransferapp.data_model.TransactionDTO
import com.ys.speedotransferapp.ui_model.TransactionDetails
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
            currency = transactionDTO.currency,
            amount = transactionDTO.amount.toString(),
            header = if (transactionDTO.status == FAILED) "Failed Transaction" else "Successful Transaction",
            largeIcon =if (transactionDTO.status == FAILED) R.drawable.failed else R.drawable.successful,
            smallIcon = if (transactionDTO.status == FAILED) R.drawable.failed_small else R.drawable.success_small
        )

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

