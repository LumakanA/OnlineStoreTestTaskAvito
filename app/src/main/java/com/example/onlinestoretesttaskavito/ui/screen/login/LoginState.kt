package com.example.onlinestoretesttaskavito.ui.screen.login

val InitialLoginState = LoginState(
    error = null,
    isNavigate = false,
    isLoading = false,
    email = "",
    errorEmail = false,
    password = "",
    buttonEnabled = false
)

data class LoginState(
    val error: String?,
    val isNavigate: Boolean,
    val isLoading: Boolean,
    val email: String,
    val errorEmail: Boolean,
    val password: String,
    val buttonEnabled: Boolean
)
