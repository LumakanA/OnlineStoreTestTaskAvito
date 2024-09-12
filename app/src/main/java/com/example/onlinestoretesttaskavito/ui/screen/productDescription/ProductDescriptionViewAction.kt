package com.example.onlinestoretesttaskavito.ui.screen.productDescription

sealed interface ProductDescriptionViewAction {
    data class Retry(val id: String) : ProductDescriptionViewAction
}