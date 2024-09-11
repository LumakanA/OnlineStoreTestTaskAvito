package com.example.onlinestoretesttaskavito.domain.models.getUsers

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val status: String,
    val count: Int,
    @SerializedName("Data")
    val users: List<User>
)