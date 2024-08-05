package com.ys.speedotransferapp.mock

import com.ys.speedotransferapp.constants.AppConstants.TRANSACTIONS_ENDPOINT
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.InputStreamReader
import java.nio.charset.Charset

class MockTransactionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url: HttpUrl = request.url()
        val path = url.encodedPath()

        val mockResponse = when (path) {
            TRANSACTIONS_ENDPOINT -> {
                val mockResponseBody = loadMockResponse("assets/mock_transactions.json")
                println(mockResponseBody)
                Response.Builder()
                    .code(200)
                    .message("OK")
                    .body(ResponseBody.create(MediaType.get("application/json"), mockResponseBody))
                    .protocol(okhttp3.Protocol.HTTP_1_1)
                    .request(request)
                    .build()
            }
            else -> {
                Response.Builder()
                    .code(404)
                    .message("Not Found")
                    .body(ResponseBody.create(MediaType.parse("text/plain"), "The requested resource was not found"))
                    .protocol(okhttp3.Protocol.HTTP_1_1)
                    .request(request)
                    .build()
            }
        }

        return mockResponse
    }

    private fun loadMockResponse(filename: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filename)
            ?: throw IllegalStateException("Mock file not found: $filename")
        return InputStreamReader(inputStream, Charset.forName("UTF-8")).readText()
    }
}
