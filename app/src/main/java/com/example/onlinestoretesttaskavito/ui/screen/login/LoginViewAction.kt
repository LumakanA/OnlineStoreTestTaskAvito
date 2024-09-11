package com.example.onlinestoretesttaskavito.ui.screen.login

sealed interface LoginViewAction {
    data class UpdateEmail(val email: String) : LoginViewAction
    data class UpdatePassword(val password: String) : LoginViewAction
    data object Login : LoginViewAction
    data object DismissError : LoginViewAction
}