package com.example.presentation.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")

    object DetailScreen : Screen("detail")
}
