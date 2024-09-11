package com.example.onlinestoretesttaskavito.ui.screen.registration

val InitialRegistrationState = RegistrationState(
    error = null,
    isNavigate = false,
    isLoading = false,
    name = "",
    email = "",
    errorName = false,
    errorEmail = false,
    password = "",
    confirmPassword = "",
    errorPassword = false,
    errorConfirmPassword = false,
    buttonEnabled = false
)

data class RegistrationState(
    val error: String?,
    val isNavigate: Boolean,
    val isLoading: Boolean,
    val name: String,
    val email: String,
    val errorName: Boolean,
    val errorEmail: Boolean,
    val password: String,
    val confirmPassword: String,
    val errorPassword: Boolean,
    val errorConfirmPassword: Boolean,
    val buttonEnabled: Boolean
)