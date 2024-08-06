package com.ys.speedotransferapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ys.speedotransferapp.api.TransactionAPIService
import com.ys.speedotransferapp.constants.AppConstants.BEARER
import com.ys.speedotransferapp.mapper.TransactionMapper
import com.ys.speedotransferapp.ui_model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionsPagingSource(
    private val token: String,
    private val apiService: TransactionAPIService
) : PagingSource<Int, Transaction>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Transaction> {
        return try {
            withContext(Dispatchers.IO) {
                val page = params.key ?: 1
                val pageSize = params.loadSize
                val response = apiService.callable.getTransactions(page, pageSize, BEARER + token)
                val transactions = TransactionMapper.mapToView(response)

                val nextKey = if (transactions.isEmpty()) {
                    null // No more pages
                } else {
                    page + 1 // Assuming incremental page numbers
                }

                LoadResult.Page(
                    data = transactions,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = nextKey
                )
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Transaction>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
