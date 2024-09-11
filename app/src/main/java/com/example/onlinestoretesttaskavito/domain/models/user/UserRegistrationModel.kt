package com.example.onlinestoretesttaskavito.domain.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationModel(
    val name: String,
    val email: String,
    val photo: String = "",
    val password: String,
    val cpassword: String,
    val isAdmin: Boolean = false,
    val addressModel: AddressModel = AddressModel(),
    val phone: String = ""
)
