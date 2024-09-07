package com.example.onlinestoretesttaskavito.ui.navigation

sealed class ScreenRouts(val route: String) {
    data object RegistrationScreen: ScreenRouts("RegistrationScreen")
    data object LoginScreen: ScreenRouts("LoginScreen")
}