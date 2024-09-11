package com.example.onlinestoretesttaskavito.domain.usecases.registration

import com.example.onlinestoretesttaskavito.data.repository.OnlineStoreRepository
import com.example.onlinestoretesttaskavito.domain.models.user.UserRegistrationModel
import com.example.onlinestoretesttaskavito.domain.response.registration.RegistrationResponse
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import retrofit2.HttpException

class RegistrationUseCase(private val repository: OnlineStoreRepository) {

    suspend fun execute(userRegistrationModel: UserRegistrationModel): ResultRequest<RegistrationResponse> {
        return try {
            val response = repository.registerUser(userRegistrationModel)

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