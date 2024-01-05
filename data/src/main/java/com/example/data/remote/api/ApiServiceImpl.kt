package com.example.data.remote.api

import com.example.data.entity.CountryEntity
import retrofit2.Response

data class ApiServiceImpl(private val countryApi: CountryApi): ApiService {
    override suspend fun getAsianCountries(): Response<List<CountryEntity>> {
        return countryApi.getAsianCountries()
    }
}