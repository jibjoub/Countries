package com.example.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.GetCountryByIdUseCase
import com.example.presentation.model.CountryUi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    getCountryById: GetCountryByIdUseCase,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel() {
    private val id: String = checkNotNull(savedStateHandle["countryId"])
    val uiState: StateFlow<DataState<CountryUi>> =
        getCountryById(id).map { state ->
            state.mapData { countryModel -> CountryUi.mapToCountryUi(countryModel) }
        }.stateIn(
            initialValue = DataState.Loading,
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
        )
}
