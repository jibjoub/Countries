package com.example.data.remote.api

import com.example.data.entity.CountryEntity
import retrofit2.Response


interface ApiService {
    suspend fun getAsianCountries(): Response<List<CountryEntity>>
}