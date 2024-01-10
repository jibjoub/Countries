package com.example.data.remote.api

import com.example.data.entity.CountryEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("all")
    suspend fun getWorldCountries(): Response<List<CountryEntity>>

    @GET("alpha/{id}")
    suspend fun getCountryById(
        @Path("id") id: String,
    ): Response<CountryEntity>
}
