package com.example.presentation.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.common.models.DataState
import com.example.presentation.model.CountryUi
import com.example.presentation.viewmodel.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(id: String) {
    // Can I give the id to an invoke function of it (knowing that it is injected with Koin)?
    // To avoid having a method in DetailViewModel
    val detailViewModel: DetailViewModel = koinViewModel()
    val uiState: DataState<CountryUi> by detailViewModel.detailUiState.collectAsState()
    detailViewModel.getDetailById(id)
    Surface(color = MaterialTheme.colorScheme.background) {
        when (val state = uiState) {
            is DataState.Success -> Text(text = state.data.commonName)
            is DataState.Loading ->
                Loading()

            else -> {
                Error()
            }
        }
    }
}
