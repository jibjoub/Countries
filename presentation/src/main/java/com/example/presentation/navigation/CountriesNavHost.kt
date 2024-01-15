package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.view.DetailScreen
import com.example.presentation.view.MainScreen
import com.example.presentation.viewmodel.DetailViewModel
import com.example.presentation.viewmodel.MainViewModel

@Composable
fun CountriesNavHost(
    mainViewModel: MainViewModel,
    detailViewModel: DetailViewModel,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(viewModel = mainViewModel)
        }
        composable(route = Screen.DetailScreen.route) {
            DetailScreen(viewModel = detailViewModel)
        }
    }
}
