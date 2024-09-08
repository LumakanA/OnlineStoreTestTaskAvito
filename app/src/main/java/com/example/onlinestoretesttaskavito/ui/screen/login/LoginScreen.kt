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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle

private val DefaultStartPadding = 25.dp
private val DefaultTopTextPadding = 20.dp

@Composable
fun LoginScreen(
    vm: LoginViewModel,
    navController: NavController
) {
    val state = vm.state

    LaunchedEffect(state.error) {
        if (state.error == "true") {
            navController.navigate(ScreenRouts.RegistrationScreen.route)
        }
    }

    if (state.error != null && state.error != "true") {
        AlertDialog(
            onDismissRequest = { vm.dismissError() },
            title = { Text(text = "An error occurred") },
            text = { Text(text = state.error.toString()) },
            confirmButton = {
                Button(onClick = { vm.dismissError() }) {
                    Text(text = "Ok")
                }
            }
        )
    }

    Scaffold { containerPadding ->
        if (!state.isLoading) {
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
                        text = stringResource(R.string.login),
                        style = defaultTextStyle.textStyle4.copy(color = White),
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = DefaultTopTextPadding),
                        value = state.email,
                        onValueChange = { newEmail ->
                            vm.updateEmail(newEmail)
                        },
                        hintText = stringResource(R.string.phone_or_email),
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = DefaultTopTextPadding),
                        value = state.password,
                        onValueChange = { newPassword ->
                            vm.updatePassword(newPassword)
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
                        buttonEnabled = state.buttonEnabled,
                        onClick = {
                            //     if (state.buttonEnabled) vm.signUp()
                        },
                        backgroundColor = White
                    )
                }
            }

        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        vm = LoginViewModel(),
        navController = rememberNavController()
    )
}