package com.example.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.presentation.navigation.CountriesNavHost
import com.example.presentation.viewmodel.DetailViewModel
import com.example.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    // TODO do not create the detailVM here, it shouldn't be in the MainActivity
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesNavHost(mainViewModel, detailViewModel)
        }
    }
}
