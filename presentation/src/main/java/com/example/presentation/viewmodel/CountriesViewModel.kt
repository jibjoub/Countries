package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.FetchWorldCountriesUseCase
import com.example.presentation.model.CountryUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CountriesViewModel(private val fetchWorldCountriesUseCase: FetchWorldCountriesUseCase) :
    ViewModel() {
    private val _countriesUiState = MutableLiveData<DataState<List<CountryUi>>>()
    val countriesUiState: LiveData<DataState<List<CountryUi>>> get() = _countriesUiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun loadWorldCountriesData() {
        _countriesUiState.value = DataState.Loading
        viewModelScope.launch {
            fetchWorldCountriesUseCase.invoke().map { dataState ->
                dataState.mapData { countryModels ->
                    countryModels.map { CountryUi.mapToCountryUi(it) }
                }
            }.collect { transformedState ->
                _countriesUiState.value = transformedState
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            loadWorldCountriesData()
            _isRefreshing.emit(false)
        }
    }
}
