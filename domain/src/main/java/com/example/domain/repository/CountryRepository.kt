package com.example.domain.repository

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun fetchCountries(): Flow<DataState<List<CountryModel>>>
}