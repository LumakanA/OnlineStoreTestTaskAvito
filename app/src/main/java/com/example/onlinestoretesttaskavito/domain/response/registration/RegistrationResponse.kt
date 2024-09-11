package com.example.onlinestoretesttaskavito.domain.response.registration

data class RegistrationResponse(
    val status: String,
    val count: Int,
    val dataProduct: List<UserDataResponse>
)