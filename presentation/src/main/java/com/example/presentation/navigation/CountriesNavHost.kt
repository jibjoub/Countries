package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.view.DetailScreen
import com.example.presentation.view.MainScreen

@Composable
fun CountriesNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen()
        }
        composable(route = Screen.DetailScreen.route) {
            DetailScreen()
        }
    }
}
