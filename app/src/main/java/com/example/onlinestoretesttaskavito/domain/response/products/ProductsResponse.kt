package com.example.onlinestoretesttaskavito.domain.response.products

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    val status: String,
    val count: Int,
    @SerializedName("Data")
    val data: List<Product>
)

data class Product(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val category: List<String>,
    val price: Double,
    @SerializedName("discounted_price")
    val discountedPrice: Double,
    val images: List<String>,
    val description: String,
    @SerializedName("product_rating")
    val productRating: Double,
    val brand: String,
    @SerializedName("product_specifications")
    val productSpecifications: List<ProductSpecification>
)

data class ProductSpecification(
    val key: String?,
    val value: String?
)
