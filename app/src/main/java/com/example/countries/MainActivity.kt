package com.example.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.countries.ui.theme.CountriesTheme
import com.example.presentation.navigation.CountriesNavHost

// A bit weird that the MainActivity is the one launching the NavHost :/ TODO check if there is not a better place to do so
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesTheme {
                CountriesNavHost(navController = rememberNavController())
            }
        }
    }
}
