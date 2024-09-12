package com.example.onlinestoretesttaskavito.ui.screen.productList

sealed interface ProductListViewAction {
    data object FetchProducts : ProductListViewAction
    data class FilterByCategory(val category: String) : ProductListViewAction
}