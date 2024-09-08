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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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

private val DefaultStartPadding = 25.dp
private val DefaultTopTextPadding = 20.dp

@Composable
fun RegistrationScreen(
    vm: RegistrationViewModel,
    navController: NavController
) {
    val state = vm.state
    val bottomItems = BottomItemScreen.entries.toTypedArray()

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
                        text = stringResource(R.string.registration),
                        style = defaultTextStyle.textStyle4.copy(color = White),
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 80.dp),
                        value = state.fullName,
                        onValueChange = { newName ->
                            vm.updateName(newName)
                        },
                        hintText = stringResource(R.string.name),
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = DefaultTopTextPadding),
                        value = state.email,
                        onValueChange = { newEmail ->
                            vm.updateEmail(newEmail)
                        },
                        hintText = stringResource(R.string.email),
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = DefaultTopTextPadding),
                        value = state.password,
                        onValueChange = { newPassword ->
                            vm.updatePassword(newPassword)
                        },
                        hintText = stringResource(R.string.password),
                    )
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = DefaultTopTextPadding),
                        value = state.confirmPassword,
                        onValueChange = { newConfirmPassword ->
                            vm.updateConfirmPassword(newConfirmPassword)
                        },
                        hintText = stringResource(R.string.confirm_password),
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 70.dp),
                        text = stringResource(R.string.login),
                        buttonEnabled = state.buttonEnabled,
                        onClick = {
                            //     if (state.buttonEnabled) vm.signUp()
                        }
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
private fun RegistrationScreenPreview() {
    RegistrationScreen(
        vm = RegistrationViewModel(),
        navController = rememberNavController()
    )
}