package com.example.onlinestoretesttaskavito.domain.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginModel(
    val email: String,
    val password: String,
)
