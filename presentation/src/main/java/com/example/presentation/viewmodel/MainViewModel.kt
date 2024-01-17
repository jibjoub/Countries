package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.GetWorldCountriesUseCase
import com.example.presentation.model.CountryUi
import kotlinx.coroutines.flow.map

class MainViewModel(getWorldCountriesUseCase: GetWorldCountriesUseCase) :
    ViewModel() {
    // I initially used a Flow here but it did trigger the remission every time the related
    // Screen becomes visible again after being invisible for more than X seconds.
    // https://bladecoder.medium.com/kotlins-flow-in-viewmodels-it-s-complicated-556b472e281a
    val countriesUiState: LiveData<DataState<List<CountryUi>>> =
        getWorldCountriesUseCase().map { dataState ->
            dataState.mapData { countryModels ->
                countryModels.map { CountryUi.mapToCountryUi(it) }
            }
        }.asLiveData()
}
