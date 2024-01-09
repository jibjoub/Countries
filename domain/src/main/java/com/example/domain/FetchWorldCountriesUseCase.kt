package com.example.domain

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow

class FetchWorldCountriesUseCase(private val countriesRepository: WorldRepository) {
    operator fun invoke(): Flow<DataState<List<CountryModel>>> = countriesRepository.fetchWorldCountries()
}
