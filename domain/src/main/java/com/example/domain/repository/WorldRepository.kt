package com.example.domain.repository

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface WorldRepository {
    fun getWorldCountries(): Flow<DataState<List<CountryModel>>>

    fun getCountryById(countryId: String): Flow<DataState<CountryModel>>
}
