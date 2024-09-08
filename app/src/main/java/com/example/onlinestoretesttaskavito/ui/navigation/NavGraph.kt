package com.example.onlinestoretesttaskavito.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onlinestoretesttaskavito.ui.screen.login.LoginScreen
import com.example.onlinestoretesttaskavito.ui.screen.login.LoginViewModel
import com.example.onlinestoretesttaskavito.ui.screen.registration.RegistrationScreen
import com.example.onlinestoretesttaskavito.ui.screen.registration.RegistrationViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    startScreen: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startScreen
    ) {
        composable(ScreenRouts.RegistrationScreen.route) {
            RegistrationScreen(vm = RegistrationViewModel(), navController = navHostController)
        }
        composable(ScreenRouts.LoginScreen.route) {
            LoginScreen(vm = LoginViewModel(), navController = navHostController)
        }
    }
}