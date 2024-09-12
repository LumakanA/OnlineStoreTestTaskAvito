package com.example.onlinestoretesttaskavito.data.repository

import com.example.onlinestoretesttaskavito.domain.models.user.UserLoginModel
import com.example.onlinestoretesttaskavito.domain.models.user.UserRegistrationModel
import com.example.onlinestoretesttaskavito.domain.response.login.LoginResponse
import com.example.onlinestoretesttaskavito.domain.response.products.ProductResponse
import com.example.onlinestoretesttaskavito.domain.response.products.ProductsResponse
import com.example.onlinestoretesttaskavito.domain.response.registration.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OnlineStoreRepository {
    // Регистрация пользователя
    @POST("users")
    suspend fun registerUser(@Body userRegistrationModel: UserRegistrationModel): RegistrationResponse

    // Авторизация пользователя
    @POST("users/auth/login")
    suspend fun loginUser(@Body userLoginModel: UserLoginModel): LoginResponse

    // Получение списка продуктов с фильтрацией по категории (если указана)
    @GET("products")
    suspend fun getAllProducts(
        @Query("category") category: String? = null // Добавлен параметр для фильтрации по категории
    ): ProductsResponse

    // Получение информации о конкретном продукте
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): ProductResponse
}