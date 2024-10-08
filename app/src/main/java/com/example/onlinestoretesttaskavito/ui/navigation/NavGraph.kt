package com.example.onlinestoretesttaskavito.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.onlinestoretesttaskavito.ui.screen.empty.EmptyScreen
import com.example.onlinestoretesttaskavito.ui.screen.login.LoginScreen
import com.example.onlinestoretesttaskavito.ui.screen.productDescription.ProductDescriptionScreen
import com.example.onlinestoretesttaskavito.ui.screen.productList.ProductListScreen
import com.example.onlinestoretesttaskavito.ui.screen.registration.RegistrationScreen
import org.koin.androidx.compose.koinViewModel

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
            RegistrationScreen(vm = koinViewModel(), navController = navHostController)
        }
        composable(ScreenRouts.LoginScreen.route) {
            LoginScreen(vm = koinViewModel(), navController = navHostController)
        }
        composable(ScreenRouts.ProductListScreen.route) {
            ProductListScreen(vm = koinViewModel(), navController = navHostController)
        }
        composable<ProductNavigation> {
            val productNavigation = it.toRoute<ProductNavigation>()
            ProductDescriptionScreen(
                vm = koinViewModel(),
                navController = navHostController,
                productNavigation.id
            )
        }
        composable(ScreenRouts.EmptyScreen.route) {
            EmptyScreen(navController = navHostController)
        }
    }
}