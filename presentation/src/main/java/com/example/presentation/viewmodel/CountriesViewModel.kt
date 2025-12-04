package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.common.models.DataState
import com.example.common.models.DataState.Loading.mapData
import com.example.domain.usecase.GetWorldCountriesUseCase
import com.example.presentation.model.CountryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel
    @Inject
    constructor(getWorldCountries: GetWorldCountriesUseCase) :
    ViewModel() {
        // I initially used a Flow here. But it updated the state every time the MainScreen
        // became visible again after being invisible for more than X seconds. It was problematic when
        // the user closed the DetailScreen and the list of Countries would change in MainScreen.
        // https://bladecoder.medium.com/kotlins-flow-in-viewmodels-it-s-complicated-556b472e281a
        val uiState: LiveData<DataState<List<CountryUi>>> =
            getWorldCountries().map { dataState ->
                dataState.mapData { countryModels ->
                    countryModels.map { CountryUi.mapToCountryUi(it) }
                }
            }.asLiveData()
    }
