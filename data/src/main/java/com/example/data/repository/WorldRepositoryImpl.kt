package com.example.data.repository

import com.example.common.models.DataState
import com.example.data.db.CountryDao
import com.example.data.db.CountryEntityDb
import com.example.data.remote.api.CountryApi
import com.example.data.remote.entity.mapToCountryModel
import com.example.domain.model.CountryModel
import com.example.domain.repository.WorldRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorldRepositoryImpl
    @Inject
    constructor(private val countryApi: CountryApi, private val countryDao: CountryDao) : WorldRepository {
        override fun getWorldCountries(): Flow<DataState<List<CountryModel>>> =
            flow { emit(countryApi.getWorldCountries()) }.map { response ->
                if (response.isSuccessful.not()) {
                    return@map DataState.Error(Exception("Response is not successful. Code: ${response.code()}"))
                }
                // deleteAll()
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
                insertCountry(
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                )

                when (val countryResponse = response.body()?.firstOrNull()) {
                    null -> DataState.Error(Exception("Response body is null"))
                    else -> DataState.Success(countryResponse.mapToCountryModel())
                }
            }.catch {
                emit(DataState.Error(Exception(it)))
            }

        override suspend fun insertCountry(
            name: String?,
            capitals: String?,
            continents: String?,
            flagUrl: String?,
            flagDescription: String?,
            population: String?,
        ) {
            countryDao.insertCountry(
                CountryEntityDb(
                    name = name,
                    capitals = capitals,
                    continents = continents,
                    flagUrl = flagUrl,
                    flagDescription = flagDescription,
                    population = population,
                ),
            )
        }

        override suspend fun deleteAll() {
            countryDao.deleteAll()
        }
    }
