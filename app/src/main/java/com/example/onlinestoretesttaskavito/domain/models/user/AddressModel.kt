package com.example.onlinestoretesttaskavito.domain.models.user

import kotlinx.serialization.Serializable

@Serializable
data class AddressModel(
    val street: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val country: String = ""
)