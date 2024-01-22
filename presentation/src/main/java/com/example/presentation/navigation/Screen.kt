package com.example.presentation.navigation

sealed class Screen(val route: String) {
    object Countries : Screen("countries")

    object Details : Screen("details")
}
