package com.example.qrcodegenerator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newswave.DestinationScreen
import com.example.qrcodegenerator.MainScreen
import com.example.qrcodegenerator.SplashScreen

@Composable
fun NavigationScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.SplashScreenDest.route
    ) {
        composable(route = DestinationScreen.SplashScreenDest.route) {
            SplashScreen(navController = navController)
        }

        composable(route = DestinationScreen.MainScreenDest.route) {
            MainScreen()
        }

//        composable(route = DestinationScreen.LoginScreenDest.route) {
//            LoginScreen()
//        }
    }
}