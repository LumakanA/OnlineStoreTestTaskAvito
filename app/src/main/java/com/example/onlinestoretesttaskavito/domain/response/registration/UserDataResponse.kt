package com.example.onlinestoretesttaskavito.domain.response.registration

import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val category: List<String>,
    val price: Int,
    @SerializedName("discounted_price")
    val discountedPrice: Int,
    val edit: Boolean,
    val images: List<String>,
    val description: String,
    @SerializedName("product_rating")
    val productRating: Double,
    @SerializedName("product_specifications")
    val productSpecifications: List<String>
)