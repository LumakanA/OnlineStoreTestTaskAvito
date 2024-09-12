package com.example.onlinestoretesttaskavito.ui.screen.productList

import androidx.lifecycle.viewModelScope
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import com.example.onlinestoretesttaskavito.domain.usecases.products.GetAllProductsUseCase
import com.example.onlinestoretesttaskavito.domain.usecases.products.GetFilterProductsUseCase
import com.example.onlinestoretesttaskavito.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getFilterProductsUseCase: GetFilterProductsUseCase
) :
    BaseViewModel<ProductListState, ProductListViewAction>(InitialProductListState) {
    override fun onAction(action: ProductListViewAction) =
        when (action) {
            is ProductListViewAction.FetchProducts -> fetchProducts()
            is ProductListViewAction.FilterByCategory -> fetchFilteredProducts(action.category)
        }

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        reduceState { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = getAllProductsUseCase.execute()) {
                is ResultRequest.Success -> {
                    val products = result.data
                    reduceState {
                        it.copy(isLoading = false, products = products, error = null)
                    }
                }

                is ResultRequest.Error -> {
                    reduceState {
                        it.copy(isLoading = false, error = result.exception.message)
                    }
                }
            }
        }
    }

    private fun fetchFilteredProducts(category: String) {
        reduceState { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = getFilterProductsUseCase.execute(category)) {
                is ResultRequest.Success -> {
                    val products = result.data
                    reduceState {
                        it.copy(isLoading = false, products = products, error = null)
                    }
                }

                is ResultRequest.Error -> {
                    reduceState {
                        it.copy(isLoading = false, error = result.exception.message)
                    }
                }
            }
        }
    }
}