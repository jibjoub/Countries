package com.example.presentation.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.viewmodel.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen() {
    val detailViewModel: DetailViewModel = koinViewModel()
    Text("Detail Screen")
}
