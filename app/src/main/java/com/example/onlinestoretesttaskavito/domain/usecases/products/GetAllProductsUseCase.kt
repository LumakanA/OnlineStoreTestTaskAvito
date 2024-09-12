package com.example.onlinestoretesttaskavito.domain.usecases.products

import com.example.onlinestoretesttaskavito.data.repository.OnlineStoreRepository
import com.example.onlinestoretesttaskavito.domain.response.products.Product
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import retrofit2.HttpException

class GetAllProductsUseCase(
    private val repository: OnlineStoreRepository
) {
    suspend fun execute(): ResultRequest<List<Product>> {
        return try {
            val response = repository.getAllProducts()
            ResultRequest.Success(response.data)
        } catch (e: HttpException) {
            ResultRequest.Error(e)
        } catch (e: Exception) {
            ResultRequest.Error(e)
        }
    }
}
