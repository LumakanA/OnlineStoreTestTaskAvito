package com.example.onlinestoretesttaskavito.ui.screen.productList

val InitialProductListState = ProductListState(
    error = null,
    isNavigate = false,
    isLoading = false,
    buttonEnabled = false
)

data class ProductListState(
    val error: String?,
    val isNavigate: Boolean,
    val isLoading: Boolean,
    val buttonEnabled: Boolean
)
