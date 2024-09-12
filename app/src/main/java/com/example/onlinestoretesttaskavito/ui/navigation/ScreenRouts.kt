package com.example.onlinestoretesttaskavito.ui.navigation

sealed class ScreenRouts(val route: String) {
    data object RegistrationScreen : ScreenRouts("RegistrationScreen")
    data object LoginScreen : ScreenRouts("LoginScreen")
    data object ProductListScreen : ScreenRouts("ProductListScreen")
    data object EmptyScreen : ScreenRouts("EmptyScreen")
}