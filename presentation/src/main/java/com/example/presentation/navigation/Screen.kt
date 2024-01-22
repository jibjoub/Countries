package com.example.presentation.navigation

sealed class Screen(val route: String) {
    object CountriesScreen : Screen("countries")

    object DetailScreen : Screen("detail")
}
