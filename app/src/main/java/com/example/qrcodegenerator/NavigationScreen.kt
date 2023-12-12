package com.example.qrcodegenerator

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newswave.DestinationScreen

@Composable
fun NavigationScreen() {

     val ID_KEY = "ID_key"
     val TEXT_KEY = "TEXT_key"
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.SplashScreenDest.route
    ) {
        composable(route = DestinationScreen.SplashScreenDest.route) {
            SplashScreen(navController = navController)
        }

        composable(route = DestinationScreen.LoginScreenDest.route) {
            LoginScreen(navController = navController)
        }

        composable(route = DestinationScreen.SignupScreenDest.route) {
            SignUp(navController = navController)
        }

        composable(route = DestinationScreen.HomeScreenDest.route) {

            HomeScreen(navController = navController)
        }

        composable(route = DestinationScreen.MainScreenDest.route, arguments = listOf(
            navArgument(name = ID_KEY) {
                type = NavType.StringType
            },
            navArgument(name = TEXT_KEY) {
                type = NavType.StringType
            }
        )) {
                navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString(ID_KEY)
            val text = navBackStackEntry.arguments?.getString(TEXT_KEY)
            MainScreen(navController = navController,id ,text)
        }
    }
}