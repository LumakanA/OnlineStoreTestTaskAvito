package com.example.onlinestoretesttaskavito.domain.results

interface ErrorMessageProvider {
    fun getErrorMessage(code: Int, message: String?): String
}