package com.example.onlinestoretesttaskavito.domain.usecases.login

import com.example.onlinestoretesttaskavito.data.repository.OnlineStoreRepository
import com.example.onlinestoretesttaskavito.domain.models.user.UserLoginModel
import com.example.onlinestoretesttaskavito.domain.response.login.LoginResponse
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import retrofit2.HttpException

class LoginUseCase(private val repository: OnlineStoreRepository) {

    suspend fun execute(userLoginModel: UserLoginModel): ResultRequest<LoginResponse> {
        return try {
            val response = repository.loginUser(userLoginModel)

            if (response.status == "success") {
                ResultRequest.Success(response)
            } else {
                ResultRequest.Error(Exception(response.status))
            }
        } catch (e: HttpException) {
            ResultRequest.Error(e)
        } catch (e: Exception) {
            ResultRequest.Error(e)
        }
    }
}