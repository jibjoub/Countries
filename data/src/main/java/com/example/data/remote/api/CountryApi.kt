package com.example.data.remote.api

import com.example.data.entity.CountryEntity
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("region/europe")
    suspend fun getAsianCountries(): Response<List<CountryEntity>>
}