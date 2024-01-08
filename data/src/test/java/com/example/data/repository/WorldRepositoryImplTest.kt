package com.example.data.repository

import com.example.data.entity.CountryEntity
import com.example.data.remote.api.RetrofitInstance
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class WorldRepositoryImplTest {
    private val retrofitInstance: RetrofitInstance = mockk()
    private val worldRepositoryImpl: WorldRepositoryImpl = WorldRepositoryImpl(retrofitInstance)

    @Test
    fun `Given a CountryEntity with all null fields, when mapping to CountryModel, the fields are not defined for Strings and null for the other types`() {
        val emptyCountryEntity = CountryEntity(null, null, null, null, null)
        val emptyCountryModel = worldRepositoryImpl.mapToCountryModel(emptyCountryEntity)

        assertEquals(emptyCountryModel.id, "Not defined")
        assertEquals(emptyCountryModel.commonName, "Not defined")
        assertEquals(emptyCountryModel.continents, null)
        assertEquals(emptyCountryModel.flagUrl, "Not defined")
        assertEquals(emptyCountryModel.flagDescription, "Not defined")
        assertEquals(emptyCountryModel.population, null)
    }
}
