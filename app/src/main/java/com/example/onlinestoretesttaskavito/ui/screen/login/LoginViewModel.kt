package com.example.onlinestoretesttaskavito.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.example.onlinestoretesttaskavito.domain.models.user.UserLoginModel
import com.example.onlinestoretesttaskavito.domain.results.ErrorMessageProvider
import com.example.onlinestoretesttaskavito.domain.results.ResultRequest
import com.example.onlinestoretesttaskavito.domain.usecases.login.LoginUseCase
import com.example.onlinestoretesttaskavito.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val errorMessageProvider: ErrorMessageProvider
) :
    BaseViewModel<LoginState, LoginViewAction>(InitialLoginState) {
    override fun onAction(action: LoginViewAction) =
        when (action) {
            is LoginViewAction.UpdateEmail -> onUpdateEmail(action.email)
            is LoginViewAction.UpdatePassword -> onUpdatePassword(action.password)
            is LoginViewAction.Login -> login()
            is LoginViewAction.DismissError -> onDismissError()
        }

    private fun onUpdateEmail(email: String) = reduceState {
        it.copy(
            email = email,
            errorEmail = !validateEmail(email),
            buttonEnabled = validateFields(email, it.password)
        )
    }

    private fun onUpdatePassword(password: String) = reduceState {
        it.copy(
            password = password,
            buttonEnabled = validateFields(it.email, password)
        )
    }

    private fun validateEmail(email: String): Boolean {
        val regex = Regex("""([a-z0-9]+)@([a-z0-9]{3,})\.([a-z]{2,3})""")
        return regex.matches(email)
    }

    private fun login() {
        reduceState { it.copy(isLoading = true, isNavigate = false) }

        val userLoginModel = UserLoginModel(
            email = state.value.email,
            password = state.value.password
        )

        viewModelScope.launch {
            when (val result = loginUseCase.execute(userLoginModel)) {
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


    private fun onDismissError() = reduceState {
        it.copy(error = null)
    }

    private fun validateFields(
        email: String,
        password: String,
    ): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}