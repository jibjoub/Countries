package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.GetCountryByIdUseCase
import com.example.presentation.model.CountryUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailViewModel(private val getCountryByIdUseCase: GetCountryByIdUseCase) :
    ViewModel() {
    private val _detailUiState = MutableStateFlow<DataState<CountryUi>>(DataState.Loading)
    val detailUiState: StateFlow<DataState<CountryUi>> = _detailUiState.asStateFlow()

    fun getDetailById(id: String) {
        viewModelScope.launch {
            val state =
                getCountryByIdUseCase.invoke(id).map { state ->
                    state.mapData { countryModel -> CountryUi.mapToCountryUi(countryModel) }
                }.collect {
                    _detailUiState.value = it
                }
        }
    }
}
