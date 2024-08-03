package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.model.TransactionItem

class TransactionsSource {
    fun getTransactions(): List<TransactionItem> {
        val transactions = mutableListOf<TransactionItem>().apply {
            add(
                TransactionItem(
                    recipientName = "John Doe",
                    paymentMethod = "Credit Card",
                    lastFourDigits = "1234",
                    dateTime = "Today 11:00",
                    status = "Completed",
                    cardHolderName = "John Doe",
                    amount = "$100.00",
                    currency = "USD",
                    id = "TXN123456",
                    cvv = "123",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Alice Johnson",
                    paymentMethod = "PayPal",
                    lastFourDigits = "9101",
                    dateTime = "Yesterday 14:30",
                    status = "Failed",
                    cardHolderName = "Alice Johnson",
                    amount = "$150.00",
                    currency = "USD",
                    id = "TXN345678",
                    cvv = "789",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Jane Smith",
                    paymentMethod = "Debit Card",
                    lastFourDigits = "5678",
                    dateTime = "8 August 12:00",
                    status = "Pending",
                    cardHolderName = "Jane Smith",
                    amount = "$200.00",
                    currency = "USD",
                    id = "TXN789012",
                    cvv = "456",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Bob Brown",
                    paymentMethod = "Bank Transfer",
                    lastFourDigits = "1121",
                    dateTime = "7 August 09:00",
                    status = "Completed",
                    cardHolderName = "Bob Brown",
                    amount = "€300.00",
                    currency = "EUR",
                    id = "TXN901234",
                    cvv = "012",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Charlie Davis",
                    paymentMethod = "Credit Card",
                    lastFourDigits = "3141",
                    dateTime = "6 August 16:45",
                    status = "Pending",
                    cardHolderName = "Charlie Davis",
                    amount = "$250.00",
                    currency = "USD",
                    id = "TXN567890",
                    cvv = "345",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Diana Evans",
                    paymentMethod = "Debit Card",
                    lastFourDigits = "5161",
                    dateTime = "5 August 11:15",
                    status = "Completed",
                    cardHolderName = "Diana Evans",
                    amount = "€400.00",
                    currency = "EUR",
                    id = "TXN678901",
                    cvv = "678",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Eve Foster",
                    paymentMethod = "PayPal",
                    lastFourDigits = "7181",
                    dateTime = "4 August 13:20",
                    status = "Failed",
                    cardHolderName = "Eve Foster",
                    amount = "$500.00",
                    currency = "USD",
                    id = "TXN789012",
                    cvv = "901",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Frank Green",
                    paymentMethod = "Bank Transfer",
                    lastFourDigits = "9202",
                    dateTime = "3 August 10:50",
                    status = "Completed",
                    cardHolderName = "Frank Green",
                    amount = "$600.00",
                    currency = "USD",
                    id = "TXN890123",
                    cvv = "234",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Grace Hill",
                    paymentMethod = "Credit Card",
                    lastFourDigits = "1222",
                    dateTime = "2 August 15:30",
                    status = "Pending",
                    cardHolderName = "Grace Hill",
                    amount = "€700.00",
                    currency = "EUR",
                    id = "TXN901234",
                    cvv = "567",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Henry Ivy",
                    paymentMethod = "Debit Card",
                    lastFourDigits = "3242",
                    dateTime = "1 August 12:00",
                    status = "Completed",
                    cardHolderName = "Henry Ivy",
                    amount = "€800.00",
                    currency = "EUR",
                    id = "TXN012345",
                    cvv = "890",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
        }
        return transactions
    }

    fun getRecentTransactions(): List<TransactionItem> {
        val transactions = mutableListOf<TransactionItem>().apply {
            add(
                TransactionItem(
                    recipientName = "John Doe",
                    paymentMethod = "Credit Card",
                    lastFourDigits = "1234",
                    dateTime = "Today 11:00",
                    status = "Completed",
                    cardHolderName = "John Doe",
                    amount = "$100.00",
                    currency = "USD",
                    id = "TXN123456",
                    cvv = "123",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Alice Johnson",
                    paymentMethod = "PayPal",
                    lastFourDigits = "9101",
                    dateTime = "Yesterday 14:30",
                    status = "Failed",
                    cardHolderName = "Alice Johnson",
                    amount = "$150.00",
                    currency = "USD",
                    id = "TXN345678",
                    cvv = "789",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Jane Smith",
                    paymentMethod = "Debit Card",
                    lastFourDigits = "5678",
                    dateTime = "8 August 12:00",
                    status = "Pending",
                    cardHolderName = "Jane Smith",
                    amount = "$200.00",
                    currency = "USD",
                    id = "TXN789012",
                    cvv = "456",
                    paymentProcessorIcon = R.drawable.mastercard,
                    cardType = "Visa"
                )
            )
        }
        return transactions
    }
}