package com.example.onlinestoretesttaskavito.domain.models.getUsers

data class User(
    val id: String,
    val name: String,
    val email: String,
    val photo: String,
    val address: Address
)