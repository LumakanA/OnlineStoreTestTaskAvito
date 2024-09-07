package com.example.onlinestoretesttaskavito.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.onlinestoretesttaskavito.data.KeyValueStorage
import com.example.onlinestoretesttaskavito.ui.navigation.NavGraph
import com.example.onlinestoretesttaskavito.ui.navigation.ScreenRouts
import com.example.onlinestoretesttaskavito.ui.theme.OnlineStoreTestTaskAvitoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            val storage = KeyValueStorage(this)
            OnlineStoreTestTaskAvitoTheme {
                NavGraph(
                    navHostController = navController, startScreen =
                    ScreenRouts.RegistrationScreen.route
                )
            }
        }
    }
}
