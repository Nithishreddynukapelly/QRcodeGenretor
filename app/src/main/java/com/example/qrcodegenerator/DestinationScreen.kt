package com.example.newswave

const val ID_KEY = "ID_key"
const val TEXT_KEY = "TEXT_key"
sealed class DestinationScreen(val route: String) {
    object SplashScreenDest : DestinationScreen(route = "splash_screen")
    object MainScreenDest : DestinationScreen(route = "main_screen"+ "/{$ID_KEY}" + "/{$TEXT_KEY}"){
        fun getFullRoute(id: Int, text: String): String {
            return "main_screen" + "/$id" + "/$text"
        }
    }
    object HomeScreenDest : DestinationScreen(route = "home_screen")
    object LoginScreenDest : DestinationScreen(route = "login_screen")
    object SignupScreenDest : DestinationScreen(route = "signup_screen")
}