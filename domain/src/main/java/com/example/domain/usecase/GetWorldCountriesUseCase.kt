package com.example.domain.usecase

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorldCountriesUseCase
    @Inject
    constructor(private val countriesRepository: WorldRepository) {
        operator fun invoke(): Flow<DataState<List<CountryModel>>> = countriesRepository.getWorldCountries()
    }
