package com.example.domain.usecase

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow

class GetWorldCountriesUseCase(private val countriesRepository: WorldRepository) {
    operator fun invoke(): Flow<DataState<List<CountryModel>>> = countriesRepository.getWorldCountries()
}
