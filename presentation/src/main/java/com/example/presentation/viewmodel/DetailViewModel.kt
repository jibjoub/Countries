package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.GetCountryByIdUseCase
import com.example.presentation.model.CountryUi
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(private val getCountryByIdUseCase: GetCountryByIdUseCase) :
    ViewModel() {
    val detailUiState: StateFlow<DataState<CountryUi>> =
        getCountryByIdUseCase.invoke("EST").map { state ->
            state.mapData { countryModel -> CountryUi.mapToCountryUi(countryModel) }
        }.stateIn(
            initialValue = DataState.Loading,
            scope = viewModelScope,
            started = WhileSubscribed(5_000),
        )
}
