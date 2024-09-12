package com.example.onlinestoretesttaskavito.domain.usecases.products

import com.example.onlinestoretesttaskavito.data.repository.OnlineStoreRepository
import com.example.onlinestoretesttaskavito.domain.response.products.Products
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest

class GetFilterProductsUseCase(private val repository: OnlineStoreRepository) {
    suspend fun execute(category: String? = null): ResultRequest<List<Products>> {
        return try {
            val response = repository.getAllProducts(category)
            ResultRequest.Success(response.data)
        } catch (e: Exception) {
            ResultRequest.Error(e)
        }
    }
}
