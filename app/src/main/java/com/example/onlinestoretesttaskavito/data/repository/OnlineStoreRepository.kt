package com.example.onlinestoretesttaskavito.data.repository

import com.example.onlinestoretesttaskavito.domain.models.getUsers.UserResponse
import com.example.onlinestoretesttaskavito.domain.models.user.UserLoginModel
import com.example.onlinestoretesttaskavito.domain.models.user.UserRegistrationModel
import com.example.onlinestoretesttaskavito.domain.response.login.LoginResponse
import com.example.onlinestoretesttaskavito.domain.response.registration.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OnlineStoreRepository {
    // Регистрация пользователя
    @POST("users")
    suspend fun registerUser(@Body userRegistrationModel: UserRegistrationModel): RegistrationResponse

    // Авторизация пользователя
    @POST("users/auth/login")
    suspend fun loginUser(@Body userLoginModel: UserLoginModel): LoginResponse

    // Получение списка пользователей
    @GET("users")
    suspend fun getUsers(): UserResponse
}