package com.example.onlinestoretesttaskavito.ui.screen.productList

import com.example.onlinestoretesttaskavito.domain.response.products.Product

data class ProductListState(
    val products: List<Product>,
    val error: String?,
    val isLoading: Boolean,
    val selectedCategory: String?
)

val InitialProductListState = ProductListState(
    products = emptyList(),
    error = null,
    isLoading = false,
    selectedCategory = null
)


