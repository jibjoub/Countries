package com.example.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.FetchCountriesUseCase

class CountriesViewModel(private val fetchCountriesUseCase: FetchCountriesUseCase): ViewModel() {
    val countries = fetchCountriesUseCase.invoke().asLiveData() as MutableLiveData
}