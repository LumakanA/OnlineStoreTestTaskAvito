package com.example.onlinestoretesttaskavito.ui.screen.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.example.onlinestoretesttaskavito.R
import com.example.onlinestoretesttaskavito.ui.components.AppButton
import com.example.onlinestoretesttaskavito.ui.components.AppTextField
import com.example.onlinestoretesttaskavito.ui.components.BottomItemScreen
import com.example.onlinestoretesttaskavito.ui.navigation.ScreenRouts
import com.example.onlinestoretesttaskavito.ui.theme.BackgroundColor
import com.example.onlinestoretesttaskavito.ui.theme.ButtonColorBlue
import com.example.onlinestoretesttaskavito.ui.theme.LightGrayColor
import com.example.onlinestoretesttaskavito.ui.theme.LightGrayColorMenu
import com.example.onlinestoretesttaskavito.ui.theme.White
import com.example.onlinestoretesttaskavito.ui.theme.defaultTextStyle
import org.koin.androidx.compose.koinViewModel

private val DefaultStartPadding = 25.dp
private val DefaultTopTextPadding = 20.dp


@Composable
fun RegistrationScreen(
    vm: RegistrationViewModel,
    navController: NavController
) {

    val state by vm.state.collectAsState()

    RegistrationContent(
        state = state,
        onAction = vm::onAction,
        navController = navController
    )
}

@Composable
fun RegistrationContent(
    state: RegistrationState,
    onAction: (RegistrationViewAction) -> Unit = {},
    navController: NavController
) {
    val bottomItems = BottomItemScreen.entries.toTypedArray()

    LaunchedEffect(state.isNavigate) {
        if (state.isNavigate) {
            navController.navigate(ScreenRouts.LoginScreen.route)
        }
    }

    if (state.error != null) {
        AlertDialog(
            onDismissRequest = { onAction(RegistrationViewAction.DismissError) },
            title = { Text(text = stringResource(R.string.an_error_occurred)) },
            text = { Text(text = state.error) },
            confirmButton = {
                Button(onClick = { onAction(RegistrationViewAction.DismissError) }) {
                    Text(text = stringResource(R.string.ok))
                }
            }
        )
    }

    Scaffold(
        bottomBar = {
            Surface(color = White, shadowElevation = 8.dp) {
                BottomAppBar(
                    containerColor = LightGrayColor,
                    tonalElevation = 0.dp
                ) {
                    bottomItems.forEach { item ->
                        NavigationBarItem(
                            label = {
                                Text(
                                    text = stringResource(id = item.title),
                                    style = defaultTextStyle.textStyle5.copy(color = LightGrayColorMenu)
                                )
                            },
                            selected = navController.currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = { navController.navigate(item.route) },
                            icon = {
                                Icon(
                                    modifier = Modifier.size(35.dp),
                                    painter = painterResource(id = item.icon),
                                    contentDescription = "Navigation icon",
                                    tint = if (navController.currentDestination?.hierarchy?.any { it.route == item.route } == true) {
                                        ButtonColorBlue
                                    } else {
                                        LightGrayColorMenu
                                    }
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent, // Убираем фон индикатора
                                selectedIconColor = ButtonColorBlue, // Цвет выбранной иконки
                                unselectedIconColor = LightGrayColorMenu // Цвет невыбранной иконки
                            )
                        )
                    }
                }
            }
        }
    ) { containerPadding ->
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
                    text = stringResource(R.string.registration),
                    style = defaultTextStyle.textStyle4.copy(color = White),
                )
                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 80.dp),
                    value = state.name,
                    onValueChange = {
                        onAction(RegistrationViewAction.UpdateName(it))
                    },
                    hintText = stringResource(R.string.name),
                    isError = state.errorName
                )
                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = DefaultTopTextPadding),
                    value = state.email,
                    onValueChange = {
                        onAction(RegistrationViewAction.UpdateEmail(it))
                    },
                    hintText = stringResource(R.string.email),
                    isError = state.errorEmail
                )
                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = DefaultTopTextPadding),
                    value = state.password,
                    onValueChange = {
                        onAction(RegistrationViewAction.UpdatePassword(it))
                    },
                    hintText = stringResource(R.string.password),
                    isPassword = true,
                    isPasswordHideButtonVisible = false,
                    isError = state.errorPassword
                )
                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = DefaultTopTextPadding),
                    value = state.confirmPassword,
                    onValueChange = {
                        onAction(RegistrationViewAction.UpdateConfirmPassword(it))
                    },
                    hintText = stringResource(R.string.confirm_password),
                    isPassword = true,
                    isPasswordHideButtonVisible = false,
                    isError = state.errorConfirmPassword
                )

                Spacer(modifier = Modifier.weight(1f))

                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 70.dp),
                    text = stringResource(R.string.login),
                    onClick = {
                        onAction(RegistrationViewAction.Registration)
                    },
                    loading = state.isLoading,
                    enabled = state.buttonEnabled
                )
            }
        }
    }
}


@Preview
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen(
        vm = koinViewModel(),
        navController = rememberNavController()
    )
}