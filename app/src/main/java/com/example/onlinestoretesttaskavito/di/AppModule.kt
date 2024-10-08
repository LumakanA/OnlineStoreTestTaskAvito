package com.example.onlinestoretesttaskavito.di

import com.example.onlinestoretesttaskavito.data.KeyValueStorage
import com.example.onlinestoretesttaskavito.data.repository.OnlineStoreRepository
import com.example.onlinestoretesttaskavito.data.repository.OnlineStoreRepositoryImp
import com.example.onlinestoretesttaskavito.domain.results.ErrorMessageProvider
import com.example.onlinestoretesttaskavito.domain.usecases.login.LoginUseCase
import com.example.onlinestoretesttaskavito.domain.usecases.product.GetProductByIdUseCase
import com.example.onlinestoretesttaskavito.domain.usecases.products.GetAllProductsUseCase
import com.example.onlinestoretesttaskavito.domain.usecases.products.GetFilterProductsUseCase
import com.example.onlinestoretesttaskavito.domain.usecases.registration.RegistrationUseCase
import com.example.onlinestoretesttaskavito.ui.screen.login.LoginViewModel
import com.example.onlinestoretesttaskavito.ui.screen.productDescription.ProductDescriptionViewModel
import com.example.onlinestoretesttaskavito.ui.screen.productList.ProductListViewModel
import com.example.onlinestoretesttaskavito.ui.screen.registration.RegistrationViewModel
import com.example.onlinestoretesttaskavito.utils.ErrorMessageProviderImp
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        KeyValueStorage(get())
    }
    single {
        OnlineStoreRepositoryImp(get())
    }
    single<OnlineStoreRepository> { OnlineStoreRepositoryImp(get()) }
    single<ErrorMessageProvider> { ErrorMessageProviderImp(androidContext()) }
    factory {
        RegistrationUseCase(get())
    }
    factory {
        LoginUseCase(get())
    }
    factory {
        GetAllProductsUseCase(get())
    }
    factory {
        GetFilterProductsUseCase(get())
    }
    factory {
        GetProductByIdUseCase(get())
    }
    viewModel {
        RegistrationViewModel(get(), get())
    }
    viewModel {
        LoginViewModel(get(), get())
    }
    viewModel {
        ProductListViewModel(get(), get())
    }
    viewModel {
        ProductDescriptionViewModel(get())
    }
}