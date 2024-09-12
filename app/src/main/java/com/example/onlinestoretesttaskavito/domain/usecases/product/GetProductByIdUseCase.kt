package com.example.onlinestoretesttaskavito.domain.usecases.product

import com.example.onlinestoretesttaskavito.data.repository.OnlineStoreRepository
import com.example.onlinestoretesttaskavito.domain.response.products.Product
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import retrofit2.HttpException

class GetProductByIdUseCase(
    private val repository: OnlineStoreRepository
) {
    suspend fun execute(productId: String): ResultRequest<Product> {
        return try {
            val response = repository.getProductById(productId)
            ResultRequest.Success(response.data)
        } catch (e: HttpException) {
            ResultRequest.Error(e)
        } catch (e: Exception) {
            ResultRequest.Error(e)
        }
    }
}