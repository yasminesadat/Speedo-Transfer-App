package com.ys.speedotransferapp.ui.transaction

import androidx.lifecycle.ViewModel
import com.ys.speedotransferapp.R
import com.ys.speedotransferapp.constants.AppConstants.FAILED
import com.ys.speedotransferapp.data.TransactionDetailsSource

class TransactionViewModel : ViewModel() {
    val transaction = TransactionDetailsSource().getTransaction()

    fun getHeader() =
        if (transaction.status == FAILED) "Failed Transaction" else "Successful Transaction"

    fun getIcon() =
        if (transaction.status == FAILED) R.drawable.failed_small else R.drawable.success_small

    fun getLargeIcon() =  if (transaction.status == FAILED) R.drawable.failed else R.drawable.successful
}