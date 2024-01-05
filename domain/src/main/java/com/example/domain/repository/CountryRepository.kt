package com.example.domain.repository

import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun fetchCountries(): Flow<List<CountryModel>>
}