package com.example.domain.usecase

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow

class FetchCountryByIdUseCase(private val countriesRepository: WorldRepository) {
    fun getCountryById(id: String): Flow<DataState<CountryModel>> {
        return countriesRepository.fetchCountryById(id)
    }
}
