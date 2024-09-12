package com.example.onlinestoretesttaskavito.domain.response.products

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val status: String,
    val data: Product
)

data class Product(
    @SerializedName("_id")
    val _id: String,
    val name: String,
    val category: List<String>,
    val price: String,
    @SerializedName("discounted_price")
    val discounted_price: String,
    val images: List<String>,
    val description: String,
    @SerializedName("product_rating")
    val product_rating: Double,
    val brand: String,
    @SerializedName("product_specifications")
    val product_specifications: List<Specification>
)

data class Specification(
    val key: String,
    val value: String
)
