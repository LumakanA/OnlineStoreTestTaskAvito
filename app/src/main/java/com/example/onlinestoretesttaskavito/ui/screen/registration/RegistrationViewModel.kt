package com.example.onlinestoretesttaskavito.ui.screen.registration

import androidx.lifecycle.viewModelScope
import com.example.onlinestoretesttaskavito.domain.models.user.UserRegistrationModel
import com.example.onlinestoretesttaskavito.domain.results.ErrorMessageProvider
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import com.example.onlinestoretesttaskavito.domain.usecases.registration.RegistrationUseCase
import com.example.onlinestoretesttaskavito.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registrationUseCase: RegistrationUseCase,
    private val errorMessageProvider: ErrorMessageProvider
) :
    BaseViewModel<RegistrationState, RegistrationViewAction>(InitialRegistrationState) {
    override fun onAction(action: RegistrationViewAction) =
        when (action) {
            is RegistrationViewAction.UpdateName -> onUpdateName(action.name)
            is RegistrationViewAction.UpdateEmail -> onUpdateEmail(action.email)
            is RegistrationViewAction.UpdatePassword -> onUpdatePassword(action.password)
            is RegistrationViewAction.UpdateConfirmPassword -> onUpdateConfirmPassword(action.confirmPassword)
            is RegistrationViewAction.Registration -> onRegistration()
            is RegistrationViewAction.DismissError -> onDismissError()
        }

    private fun onUpdateName(name: String) = reduceState {
        it.copy(
            name = name,
            errorName = name.isEmpty(),
            buttonEnabled = validateFields(name, it.email, it.password, it.confirmPassword)
        )
    }

    private fun onUpdateEmail(email: String) = reduceState {
        it.copy(
            email = email,
            errorEmail = email.isEmpty() || !validateEmail(email),
            buttonEnabled = validateFields(it.name, email, it.password, it.confirmPassword)
        )
    }

    private fun onUpdatePassword(password: String) = reduceState {
        it.copy(
            password = password,
            errorPassword = password.isEmpty(),
            errorConfirmPassword = passwordMismatch(password, it.confirmPassword),
            buttonEnabled = validateFields(it.name, it.email, password, it.confirmPassword)
        )
    }

    private fun onUpdateConfirmPassword(confirmPassword: String) = reduceState {
        it.copy(
            confirmPassword = confirmPassword,
            errorConfirmPassword = passwordMismatch(it.password, confirmPassword),
            buttonEnabled = validateFields(it.name, it.email, it.password, confirmPassword)
        )
    }


    private fun onDismissError() = reduceState {
        it.copy(error = null)
    }


    private fun onRegistration() {
        reduceState { it.copy(isLoading = true, isNavigate = false) }

        val userRegistrationModel = UserRegistrationModel(
            name = state.value.name,
            email = state.value.email,
            password = state.value.password,
            cpassword = state.value.confirmPassword
        )

        viewModelScope.launch {
            when (val result = registrationUseCase.execute(userRegistrationModel)) {
                is ResultRequest.Success -> {
                    reduceState {
                        it.copy(isLoading = false, error = null, isNavigate = true)
                    }
                }

                is ResultRequest.Error -> {
                    reduceState {
                        it.copy(
                            isLoading = false,
                            error = result.getErrorMessage(errorMessageProvider),
                            isNavigate = false
                        )
                    }
                }
            }
        }
    }

    // Валидация email
    private fun validateEmail(email: String): Boolean {
        val regex = Regex("""([a-z0-9]+)@([a-z0-9]{3,})\.([a-z]{2,3})""")
        return regex.matches(email)
    }

    // Проверка совпадения паролей
    private fun passwordMismatch(password: String, confirmPassword: String): Boolean {
        return password != confirmPassword
    }

    // Общая валидация полей (чтобы все поля были непустыми)
    private fun validateFields(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }
}


