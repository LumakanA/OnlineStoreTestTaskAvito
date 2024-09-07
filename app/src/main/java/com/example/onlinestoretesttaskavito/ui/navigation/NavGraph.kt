package com.example.onlinestoretesttaskavito.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
//            RegistrationScreen()
        }
        composable(ScreenRouts.LoginScreen.route) {
//            LoginScreen()
        }
    }
}