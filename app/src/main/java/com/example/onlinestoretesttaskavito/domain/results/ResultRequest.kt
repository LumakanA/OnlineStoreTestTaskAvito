package com.example.onlinestoretesttaskavito.domain.results

import retrofit2.HttpException

sealed class ResultRequest<out T> {
    data class Success<out T>(val data: T) : ResultRequest<T>()
    data class Error(val exception: Exception) : ResultRequest<Nothing>()

    // Метод для получения сообщения об ошибке
    fun getErrorMessage(provider: ErrorMessageProvider): String {
        return when (this) {
            is Error -> handleError(exception, provider)
            else -> "An unexpected error occurred"
        }
    }

    private fun handleError(exception: Exception, provider: ErrorMessageProvider): String {
        return when (exception) {
            is HttpException -> handleHttpException(exception, provider)
            else -> "Unexpected error: ${exception.localizedMessage}"
        }
    }

    private fun handleHttpException(e: HttpException, provider: ErrorMessageProvider): String {
        return provider.getErrorMessage(e.code(), e.message())
    }
}

