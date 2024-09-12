package com.example.onlinestoretesttaskavito.utils

import android.content.Context
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.domain.results.ErrorMessageProvider

class ErrorMessageProviderImp(private val context: Context) : ErrorMessageProvider {
    override fun getErrorMessage(code: Int, message: String?): String {
        return when (code) {
            400 -> context.getString(R.string.error_bad_request)
            401 -> context.getString(R.string.error_unauthorized)
            403 -> context.getString(R.string.error_forbidden)
            404 -> context.getString(R.string.error_not_found)
            409 -> context.getString(R.string.error_conflict)
            else -> context.getString(
                R.string.error_http_generic,
                message ?: context.getString(R.string.unknown_error)
            )
        }
    }
}