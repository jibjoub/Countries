package com.example.data.remote.api

import com.example.data.entity.CountryEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("all")
    suspend fun getWorldCountries(): Response<List<CountryEntity>>
}
