package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.common.models.DataState
import com.example.presentation.model.CountryUi
import com.example.presentation.view.DetailScreen
import com.example.presentation.view.MainScreen
import com.example.presentation.viewmodel.DetailViewModel
import com.example.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CountriesNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            val mainViewModel: MainViewModel = getViewModel()
            val uiState by mainViewModel.uiState.observeAsState(initial = DataState.Loading)

            MainScreen(navController, uiState)
        }
        composable(
            route = Screen.DetailScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {}),
        ) { backStackEntry ->
            val arg = backStackEntry.arguments?.getString("id")

            arg?.let {
                val detailViewModel: DetailViewModel = getViewModel { parametersOf(arg) }
                val uiState: DataState<CountryUi> by detailViewModel.uiState.collectAsState()

                DetailScreen(uiState)
            }
        }
    }
}
