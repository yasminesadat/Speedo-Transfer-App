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
                val pageNumber = getPageNumberFromUrl(url)
                val mockFile = "assets/mock_transactions_page_$pageNumber.json"
                val mockResponseBody = loadMockResponse(mockFile)
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
                    .body(
                        ResponseBody.create(
                            MediaType.parse("text/plain"),
                            "The requested resource was not found"
                        )
                    )
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

private fun getPageNumberFromUrl(url: HttpUrl): Int {
    // Assuming the page number is provided as a query parameter named "page"
    val pageNumberQuery = url.queryParameter("page")
    return pageNumberQuery?.toIntOrNull() ?: 1 // Default to 1 if the page number is not provided
}
