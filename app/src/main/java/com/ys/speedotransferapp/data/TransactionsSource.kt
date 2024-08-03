package com.ys.speedotransferapp.data

import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.model.TransactionItem

class TransactionsSource {
    fun getTransactions(): List<TransactionItem> {
        return mutableListOf<TransactionItem>().apply {
            add(
                TransactionItem(
                    recipientName = "John Doe",
                    paymentMethod = "Credit Card",
                    recipientDigits = "xxxx1234",
                    dateTime = "Today 14:23",
                    status = "Successful",
                    paymentProcessorIcon = R.drawable.visa, // Replace with actual drawable resource
                    paymentProcessor = "Visa",
                    senderName = "Jane Smith",
                    amount = "100.00 LE",
                    currency = "EGP",
                    reference = 123456,
                    senderDigits = "xxxx5678"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Alice Johnson",
                    paymentMethod = "Debit Card",
                    recipientDigits = "xxxx5678",
                    dateTime = "Yesterday 09:45",
                    status = "Failed",
                    paymentProcessorIcon = R.drawable.mastercard, // Replace with actual drawable resource
                    paymentProcessor = "Mastercard",
                    senderName = "Bob Brown",
                    amount = "€250.00",
                    currency = "EUR",
                    reference = 789012,
                    senderDigits = "xxxx1234"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Chris Evans",
                    paymentMethod = "Credit Card",
                    recipientDigits = "xxxx8765",
                    dateTime = "3 August 16:30",
                    status = "Successful",
                    paymentProcessorIcon = R.drawable.visa, // Replace with actual drawable resource
                    paymentProcessor = "Visa",
                    senderName = "Sam Wilson",
                    amount = "$150.00",
                    currency = "US",
                    reference = 345678,
                    senderDigits = "xxxx4321"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Diana Prince",
                    paymentMethod = "Debit Card",
                    recipientDigits = "xxxx4321",
                    dateTime = "1 June 11:20",
                    status = "Failed",
                    paymentProcessorIcon = R.drawable.mastercard, // Replace with actual drawable resource
                    paymentProcessor = "Mastercard",
                    senderName = "Clark Kent",
                    amount = "300.00 LE",
                    currency = "EGP",
                    reference = 901234,
                    senderDigits = "xxxx8765"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Bruce Wayne",
                    paymentMethod = "Credit Card",
                    recipientDigits = "xxxx1234",
                    dateTime = "20 May 18:45",
                    status = "Successful",
                    paymentProcessorIcon = R.drawable.visa, // Replace with actual drawable resource
                    paymentProcessor = "Visa",
                    senderName = "Selina Kyle",
                    amount = "€200.00",
                    currency = "EUR",
                    reference = 567890,
                    senderDigits = "xxxx5678"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Peter Parker",
                    paymentMethod = "Debit Card",
                    recipientDigits = "xxxx5678",
                    dateTime = "15 May 14:15",
                    status = "Failed",
                    paymentProcessorIcon = R.drawable.mastercard, // Replace with actual drawable resource
                    paymentProcessor = "Mastercard",
                    senderName = "Mary Jane",
                    amount = "$175.00",
                    currency = "US",
                    reference = 234567,
                    senderDigits = "xxxx1234"
                )
            )
            add(
                TransactionItem(
                    recipientName = "Tony Stark",
                    paymentMethod = "Credit Card",
                    recipientDigits = "xxxx8765",
                    dateTime = "1 May 09:10",
                    status = "Successful",
                    paymentProcessorIcon = R.drawable.visa, // Replace with actual drawable resource
                    paymentProcessor = "Visa",
                    senderName = "Pepper Potts",
                    amount = "€500.00",
                    currency = "EUR",
                    reference = 678901,
                    senderDigits = "xxxx4321"
                )
            )
        }
    }
    fun getRecentTransactions(): List<TransactionItem>{
        return getTransactions().take(3)
    }
}