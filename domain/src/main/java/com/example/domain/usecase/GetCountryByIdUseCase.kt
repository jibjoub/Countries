package com.example.domain.usecase

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow

class GetCountryByIdUseCase(private val countriesRepository: WorldRepository) {
    operator fun invoke(id: String): Flow<DataState<CountryModel>> {
        return countriesRepository.getCountryById(id)
    }
}
