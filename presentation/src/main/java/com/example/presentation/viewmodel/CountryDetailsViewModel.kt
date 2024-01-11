package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.FetchCountryByIdUseCase
import com.example.presentation.model.CountryUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryDetailsViewModel(private val fetchCountryByIdUseCase: FetchCountryByIdUseCase) :
    ViewModel() {
    private val _countryUiState = MutableLiveData<DataState<CountryUi>>()
    val countryUiState: LiveData<DataState<CountryUi>> = _countryUiState

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun loadCountryDetails(id: String) {
        _countryUiState.value = DataState.Loading
        viewModelScope.launch {
            fetchCountryByIdUseCase.getCountryById(id).collect { dataState ->
                _countryUiState.value = dataState.mapData { CountryUi.mapToCountryUi(it) }
            }
        }
    }
}
