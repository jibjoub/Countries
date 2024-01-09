package com.example.data.repository

import com.example.common.models.DataState
import com.example.data.entity.CountryEntity
import com.example.data.remote.api.RetrofitInstance
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WorldRepositoryImpl(private val retrofitInstance: RetrofitInstance) : WorldRepository {
    override fun fetchWorldCountries(): Flow<DataState<List<CountryModel>>> =
        flow {
            try {
                val response = retrofitInstance.apiService.getWorldCountries()
                when {
                    response.isSuccessful -> {
                        val body = response.body()
                        when {
                            body != null -> {
                                // Always give a random order to the countries fetched
                                val countries = body.shuffled().map { mapToCountryModel(it) }
                                emit(DataState.Success(countries))
                            }
                            else -> emit(DataState.Error(Exception("Response body is null")))
                        }
                    }
                    else -> emit(DataState.Error(Exception("Response is not successful. Code: ${response.code()}")))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    fun mapToCountryModel(country: CountryEntity): CountryModel {
        return CountryModel(
            id = country.id ?: "Not defined",
            commonName = country.name?.common ?: "Not defined",
            officialName = country.name?.official ?: "Not defined",
            continents = country.continents,
            flagUrl = country.flags?.url ?: "Not defined",
            flagDescription = country.flags?.description ?: "Not defined",
            population = country.population,
        )
    }
}
