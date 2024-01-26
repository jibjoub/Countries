package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.common.models.DataState
import com.example.presentation.model.CountryUi
import com.example.presentation.view.CountriesScreen
import com.example.presentation.view.DetailScreen
import com.example.presentation.viewmodel.CountriesViewModel
import com.example.presentation.viewmodel.DetailsViewModel

@Composable
fun CountriesNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Countries.route) {
        composable(route = Screen.Countries.route) {
            val countriesViewModel: CountriesViewModel = hiltViewModel()
            val uiState by countriesViewModel.uiState.observeAsState(initial = DataState.Loading)

            val onItemClick: (CountryUi) -> Unit = {
                navController.navigate(Screen.Details.route + "/" + it.id)
            }

            CountriesScreen(uiState, onItemClick)
        }
        composable(
            route = Screen.Details.route + "/{countryId}",
            arguments = listOf(navArgument("countryId") {}),
        ) {
            val detailsViewModel: DetailsViewModel = hiltViewModel()
            val uiState: DataState<CountryUi> by detailsViewModel.uiState.collectAsState()

            DetailScreen(uiState)
        }
    }
}
