package com.ys.speedotransferapp.data

class CurrencyExchangeRateSource {
    // This map will store exchange rates with the format: "FROM_TO" -> rate
    private val exchangeRates = mapOf(
        "USD_LE" to 49.55,
        "USD_USD" to 1.0,
        "LE_LE" to 1.0,
        "LE_USD" to 0.020,
    )

    fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        val key = "${fromCurrency}_${toCurrency}"
        return exchangeRates[key] ?: run {
            // If direct conversion is not available, try reverse conversion
            val reverseKey = "${toCurrency}_${fromCurrency}"
            exchangeRates[reverseKey]?.let { 1 / it } ?: 1.0 // Default to 1 if no conversion found
        }
    }

}