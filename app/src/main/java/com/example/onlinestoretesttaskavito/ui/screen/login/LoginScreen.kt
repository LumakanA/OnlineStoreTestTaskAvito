package com.example.onlinestoretesttaskavito.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.ui.components.AppButton
import com.example.onlinestoretesttaskavito.ui.components.AppTextField
import com.example.onlinestoretesttaskavito.ui.navigation.ScreenRouts
import com.example.onlinestoretesttaskavito.ui.theme.BackgroundColor
import com.example.onlinestoretesttaskavito.ui.theme.ButtonColorWhite
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle
import org.koin.androidx.compose.koinViewModel

private val DefaultStartPadding = 25.dp
private val DefaultTopTextPadding = 20.dp

@Composable
fun LoginScreen(
    vm: LoginViewModel,
    navController: NavController
) {

    val state by vm.state.collectAsState()

    LoginContent(
        state = state,
        onAction = vm::onAction,
        navController = navController
    )
}

@Composable
fun LoginContent(
    state: LoginState,
    onAction: (LoginViewAction) -> Unit = {},
    navController: NavController
) {

    LaunchedEffect(state.isNavigate) {
        if (state.isNavigate) {
            navController.navigate(ScreenRouts.RegistrationScreen.route)
        }
    }

    if (state.error != null) {
        AlertDialog(
            onDismissRequest = { onAction(LoginViewAction.DismissError) },
            title = { Text(text = stringResource(R.string.an_error_occurred)) },
            text = { Text(text = state.error) },
            confirmButton = {
                Button(onClick = { onAction(LoginViewAction.DismissError) }) {
                    Text(text = stringResource(R.string.ok))
                }
            }
        )
    }

    Scaffold { containerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(containerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = DefaultStartPadding, end = DefaultStartPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    text = stringResource(R.string.log),
                    style = defaultTextStyle.textStyle4.copy(color = White),
                )
                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = DefaultTopTextPadding),
                    value = state.email,
                    onValueChange = {
                        onAction(LoginViewAction.UpdateEmail(it))
                    },
                    hintText = stringResource(R.string.phone_or_email),
                )
                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = DefaultTopTextPadding),
                    value = state.password,
                    onValueChange = {
                        onAction(LoginViewAction.UpdatePassword(it))
                    },
                    isPassword = true,
                    hintText = stringResource(R.string.password),
                )

                Spacer(modifier = Modifier.weight(1f))

                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 100.dp),
                    text = stringResource(R.string.log),
                    enabled = state.buttonEnabled,
                    onClick = {
                        onAction(LoginViewAction.Login)
                    },
                    backgroundColor = ButtonColorWhite,
                    loading = state.isLoading
                )
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        vm = koinViewModel(),
        navController = rememberNavController()
    )
}