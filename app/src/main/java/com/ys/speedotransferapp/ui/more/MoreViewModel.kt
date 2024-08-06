package com.ys.speedotransferapp.ui.more

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.speedotransferapp.api.UserAPIService
import com.ys.speedotransferapp.data_model.LogoutRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MoreViewModel : ViewModel() {

    var showHelpBottomSheet by mutableStateOf(false)
        private set

    var logoutState by mutableStateOf<Result<String>?>(null)
        private set

    fun showHelpBottomSheet(show: Boolean) {
        showHelpBottomSheet = show
    }

    fun logout(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val logoutRequest = LogoutRequest(token = token)
                val response = UserAPIService.callable.logoutUser(logoutRequest)
                if (response.status == "Accepted") {
                    logoutState = Result.success(response.message)
                    Log.d("MoreViewModel", "Logout successful: ${response.message}")
                } else {
                    throw Exception(response.message)
                }
            } catch (e: IOException) {
                Log.e("MoreViewModel", "Network error occurred", e)
                logoutState = Result.failure(e)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("MoreViewModel", "HTTP error occurred: $errorBody", e)
                logoutState = Result.failure(e)
            } catch (e: Exception) {
                Log.e("MoreViewModel", "Unexpected error occurred", e)
                logoutState = Result.failure(e)
            }
        }
    }

    fun loadToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("auth_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }

    fun clearToken(context: Context) {
        val sharedPreferences = context.getSharedPreferences("auth_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.apply()
    }
}

