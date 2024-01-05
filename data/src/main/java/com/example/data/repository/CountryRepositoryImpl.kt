package com.example.data.repository

import com.example.data.entity.CountryEntity
import com.example.data.remote.api.RetrofitInstance
import com.example.domain.model.CountryModel
import com.example.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

class CountryRepositoryImpl(private val retrofitInstance: RetrofitInstance): CountryRepository {
    override fun fetchCountries(): Flow<List<CountryModel>> =
        flow {
            try {
                val response = retrofitInstance.apiService.getAsianCountries()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    // Always give a random order to the countries fetched
                    emit(body.shuffled().map { mapToModel(it) })
                }
                else {
                    emit(emptyList())
                }
            } catch (e: HttpException) {
                emit(emptyList())
            }
        }

    fun mapToModel(country: CountryEntity): CountryModel {
        val res = CountryModel(
            country.id,
            country.name.common,
            country.name.official,
            country.continents,
            country.flags.url,
            country.flags.description?: "Not defined"
        )
        return res
    }
}