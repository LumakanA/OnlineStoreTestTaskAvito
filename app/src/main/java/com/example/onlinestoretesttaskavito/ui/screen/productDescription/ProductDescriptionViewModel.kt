package com.example.onlinestoretesttaskavito.ui.screen.productDescription

import androidx.lifecycle.viewModelScope
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import com.example.onlinestoretesttaskavito.domain.usecases.product.GetProductByIdUseCase
import com.example.onlinestoretesttaskavito.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProductDescriptionViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : BaseViewModel<ProductDescriptionState, ProductDescriptionViewAction>(
    InitialProductDescriptionState
) {
    override fun onAction(action: ProductDescriptionViewAction) {
        when (action) {
            is ProductDescriptionViewAction.Retry -> fetchProductDetails(action.id)
        }
    }

    private fun fetchProductDetails(id: String) {
        reduceState { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = getProductByIdUseCase.execute(id)) {
                is ResultRequest.Success -> {
                    val products = result.data
                    reduceState {
                        it.copy(isLoading = false, product = products, error = null)
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
