package com.example.data.repository

import com.example.data.entity.CountryEntity
import com.example.data.remote.api.RetrofitInstance
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CountryRepositoryImplTest {
    private val retrofitInstance: RetrofitInstance = mockk()
    private val countryRepositoryImpl: CountryRepositoryImpl = CountryRepositoryImpl(retrofitInstance)

    @Test
    fun `When all the fields in a CountryEntity are null, all the String fields should have the value of Not defined, the list of Continent should be empty and the population should be null in the CountryModel`() {
        val emptyCountryEntity = CountryEntity(null, null, null, null, null)

        val emptyCountryModel = countryRepositoryImpl.mapToCountryModel(emptyCountryEntity)

        assertEquals(emptyCountryModel.id, "Not defined")
        assertEquals(emptyCountryModel.commonName, "Not defined")
        assert(emptyCountryModel.continents.isEmpty())
        assertEquals(emptyCountryModel.flagUrl, "Not defined")
        assertEquals(emptyCountryModel.flagDescription, "Not defined")
        assertEquals(emptyCountryModel.population, null)
    }
}