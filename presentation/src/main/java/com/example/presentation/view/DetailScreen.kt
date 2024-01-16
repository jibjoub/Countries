package com.example.presentation.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.viewmodel.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(id: String?) {
    // Can I give the id to an invoke function of it (knowing that it is injected with Koin)?
    // To avoid having a method in DetailViewModel
    val detailViewModel: DetailViewModel = koinViewModel()
    Text("Detail Screen $id")
}
