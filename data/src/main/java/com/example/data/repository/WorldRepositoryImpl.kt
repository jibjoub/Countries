package com.example.data.repository

import com.example.common.models.DataState
import com.example.data.entity.mapToCountryModel
import com.example.data.remote.api.CountryApi
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WorldRepositoryImpl(private val countryApi: CountryApi) : WorldRepository {
    override fun getWorldCountries(): Flow<DataState<List<CountryModel>>> =
        flow {
            try {
                val response = countryApi.getWorldCountries()
                when {
                    response.isSuccessful -> {
                        val body = response.body()
                        when {
                            body != null -> {
                                // Always give a random order to the countries fetched
                                val countries = body.shuffled().map { it.mapToCountryModel() }
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

    // Code is very similar to the function above TODO write tests for the  and then avoid repetition by using a lambda
    override fun getCountryById(countryId: String): Flow<DataState<CountryModel>> =
        flow {
            try {
                val response = countryApi.getCountryById(countryId)
                when {
                    response.isSuccessful -> {
                        val body = response.body()
                        when {
                            body != null -> {
                                emit(DataState.Success(body.first().mapToCountryModel()))
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
}
