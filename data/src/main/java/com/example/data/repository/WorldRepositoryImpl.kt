package com.example.data.repository

import com.example.common.models.DataState
import com.example.data.entity.mapToCountryModel
import com.example.data.remote.api.CountryApi
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorldRepositoryImpl
    @Inject
    constructor(private val countryApi: CountryApi) : WorldRepository {
        override fun getWorldCountries(): Flow<DataState<List<CountryModel>>> =
            flow { emit(countryApi.getWorldCountries()) }.map { response ->
                if (response.isSuccessful.not()) {
                    return@map DataState.Error(Exception("Response is not successful. Code: ${response.code()}"))
                }
                val body = response.body()
                if (body.isNullOrEmpty()) {
                    return@map DataState.Error(Exception("Response body is null or empty"))
                } else { // Always give a random order to the countries fetched
                    return@map DataState.Success(body.shuffled().map { it.mapToCountryModel() })
                }
            }.catch {
                DataState.Error(Exception(it))
            }

        override fun getCountryById(countryId: String): Flow<DataState<CountryModel>> =
            flow { emit(countryApi.getCountryById(countryId)) }.map { response ->
                if (response.isSuccessful.not()) {
                    return@map DataState.Error(Exception("Response is not successful. Code: ${response.code()}"))
                }

                when (val countryResponse = response.body()?.firstOrNull()) {
                    null -> DataState.Error(Exception("Response body is null"))
                    else -> DataState.Success(countryResponse.mapToCountryModel())
                }
            }.catch {
                emit(DataState.Error(Exception(it)))
            }
    }
