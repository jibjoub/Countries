package com.example.data.repository

import com.example.common.models.DataState
import com.example.data.entity.CountryEntity
import com.example.data.remote.api.RetrofitInstance
import com.example.domain.model.CountryModel
import com.example.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CountryRepositoryImpl(private val retrofitInstance: RetrofitInstance): CountryRepository {
    override fun fetchCountries(): Flow<DataState<List<CountryModel>>> =
        flow {
            try {
                val response = retrofitInstance.apiService.getAsianCountries()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    // Always give a random order to the countries fetched
                    val countries = body.shuffled().map { mapToCountryModel(it) }
                    emit(DataState.Success(countries))
                }
                else {
                    emit(DataState.Error(Exception("Response is not successful or body is null")))
                }
            } catch (e: HttpException) {
                emit(DataState.Error(e))
            }
        }

    fun mapToCountryModel(country: CountryEntity): CountryModel {
        return CountryModel(
            country.id?: "Not defined",
            country.name?.common?: "Not defined",
            country.name?.official?: "Not defined",
            country.continents?: listOf(),
            country.flags?.url?: "Not defined",
            country.flags?.description?: "Not defined",
            country.population
        )
    }
}