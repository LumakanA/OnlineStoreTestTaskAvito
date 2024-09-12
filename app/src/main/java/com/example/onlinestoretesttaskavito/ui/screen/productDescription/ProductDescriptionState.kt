package com.example.onlinestoretesttaskavito.ui.screen.productDescription

import com.example.onlinestoretesttaskavito.domain.response.products.Product

data class ProductDescriptionState(
    val error: String?,
    val isLoading: Boolean,
    val product: Product?,
    val productId: String
)

val InitialProductDescriptionState = ProductDescriptionState(
    error = null,
    isLoading = false,
    product = null,
    productId = ""
)


