package com.example.onlinestoretesttaskavito.data.repository

import android.util.Log
import com.example.onlinestoretesttaskavito.data.KeyValueStorage
import com.example.onlinestoretesttaskavito.domain.models.getUsers.UserResponse
import com.example.onlinestoretesttaskavito.domain.models.user.UserLoginModel
import com.example.onlinestoretesttaskavito.domain.models.user.UserRegistrationModel
import com.example.onlinestoretesttaskavito.domain.response.login.LoginResponse
import com.example.onlinestoretesttaskavito.domain.response.registration.RegistrationResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val repositoryUrl = "https://fakeshopapi-l2ng.onrender.com/app/v1/"

class OnlineStoreRepositoryImp(
    private val storage: KeyValueStorage
) : OnlineStoreRepository {

    private val api: OnlineStoreRepository by lazy {
        Retrofit.Builder()
            .baseUrl(repositoryUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OnlineStoreRepository::class.java)
    }

    override suspend fun registerUser(userRegistrationModel: UserRegistrationModel): RegistrationResponse {
        val response = api.registerUser(userRegistrationModel)
        Log.d(
            "12345q",
            "Status: ${response.status}, count: ${response.count}, dataProduct: ${response.dataProduct}"
        )
        return response
    }

    override suspend fun loginUser(userLoginModel: UserLoginModel): LoginResponse {
        val response = api.loginUser(userLoginModel)
        storage.accessToken = response.token
        Log.d("12345q", "Status: ${response.status}, Token: ${response.token}")
        return response
    }


    override suspend fun getUsers(): UserResponse {
        val response = api.getUsers()
        Log.d(
            "12345q",
            "Status: ${response.status}, count: ${response.count}, users: ${response.users}"
        )
        return response
    }
}