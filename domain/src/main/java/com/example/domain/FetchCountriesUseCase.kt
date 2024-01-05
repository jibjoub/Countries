package com.example.domain

import com.example.domain.model.CountryModel
import com.example.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class FetchCountriesUseCase(private val countriesRepository: CountryRepository) {
    operator fun invoke(): Flow<List<CountryModel>> = countriesRepository.fetchCountries()
}