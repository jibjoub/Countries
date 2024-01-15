package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.GetWorldCountriesUseCase
import com.example.presentation.model.CountryUi
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val getWorldCountriesUseCase: GetWorldCountriesUseCase) :
    ViewModel() {
    val countriesUiState: StateFlow<DataState<List<CountryUi>>> =
        getWorldCountriesUseCase.invoke().map { dataState ->
            dataState.mapData { countryModels ->
                countryModels.map { CountryUi.mapToCountryUi(it) }
            }
        }.stateIn(
            initialValue = DataState.Loading,
            scope = viewModelScope,
            started = WhileSubscribed(5_000),
        )
}
