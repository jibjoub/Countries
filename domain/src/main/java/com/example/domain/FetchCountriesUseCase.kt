package com.example.domain

import com.example.common.models.DataState
import com.example.domain.model.CountryModel
import com.example.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow

class FetchCountriesUseCase(private val countriesRepository: CountryRepository) {
    // TODO decide if the transformation to CountryUi should be made here
    operator fun invoke(): Flow<DataState<List<CountryModel>>> = countriesRepository.fetchCountries()

//    fun mapToCountryUi(countryModel: CountryModel): CountryUi {
//
//    }
}
