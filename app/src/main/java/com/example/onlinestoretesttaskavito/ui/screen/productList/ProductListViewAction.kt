package com.example.onlinestoretesttaskavito.ui.screen.productList

sealed interface ProductListViewAction {
    data object ProductList : ProductListViewAction
}