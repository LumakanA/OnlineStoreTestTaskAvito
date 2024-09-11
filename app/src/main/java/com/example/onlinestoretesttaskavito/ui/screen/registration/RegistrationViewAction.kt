package com.example.onlinestoretesttaskavito.ui.screen.registration

sealed interface RegistrationViewAction {
    data class UpdateName(val name: String) : RegistrationViewAction
    data class UpdateEmail(val email: String) : RegistrationViewAction
    data class UpdatePassword(val password: String) : RegistrationViewAction
    data class UpdateConfirmPassword(val confirmPassword: String) : RegistrationViewAction
    data object Registration : RegistrationViewAction
    data object DismissError : RegistrationViewAction
}