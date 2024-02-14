package com.example.domain.repository

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface WorldRepository {
    fun getWorldCountries(): Flow<DataState<List<CountryModel>>>

    fun getCountryById(countryId: String): Flow<DataState<CountryModel>>

    suspend fun insertCountry(
        name: String?,
        continents: String?,
        flagUrl: String?,
        flagDescription: String?,
        population: String?,
    )

    suspend fun insertCapital(
        name: String?,
        countryName: String?,
    )

    suspend fun insertContinent(name: String?)

    suspend fun insertCountryContinentCrossRef(
        countryName: String,
        continentName: String,
    )

    suspend fun deleteAll()
}
