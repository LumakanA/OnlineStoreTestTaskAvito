package com.example.onlinestoretesttaskavito.ui.screen.productList

import com.example.onlinestoretesttaskavito.ui.base.BaseViewModel
import com.example.onlinestoretesttaskavito.ui.screen.login.InitialLoginState
import com.example.onlinestoretesttaskavito.ui.screen.login.LoginState

class ProductListViewModel :
    BaseViewModel<LoginState, ProductListViewAction>(InitialLoginState) {
    override fun onAction(action: ProductListViewAction) =
        when (action) {
            is ProductListViewAction.ProductList -> productList()
        }

    private fun productList() {
    }
}