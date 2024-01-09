package com.example.data.repository

import com.example.data.entity.CountryEntity
import com.example.data.entity.FlagEntity
import com.example.data.entity.NameEntity
import com.example.data.remote.api.RetrofitInstance
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class WorldRepositoryImplTest {
    private val retrofitInstance: RetrofitInstance = mockk()
    private val worldRepositoryImpl = WorldRepositoryImpl(retrofitInstance)

    @Test
    fun `Given a CountryEntity, when mapping to CountryModel, the fields should be correctly mapped`() {
        val emptyCountryEntity =
            CountryEntity(
                "EST",
                NameEntity("Estonia", "Republic of Estonia"),
                listOf("Tallinn"),
                listOf("Europe"),
                FlagEntity("test", "test"),
                1_331_057,
            )
        val emptyCountryModel = worldRepositoryImpl.mapToCountryModel(emptyCountryEntity)

        assertEquals(emptyCountryModel.id, emptyCountryEntity.id)
        assertEquals(emptyCountryModel.commonName, emptyCountryEntity.name?.common)
        assertEquals(emptyCountryModel.continents, emptyCountryEntity.continents)
        assertEquals(emptyCountryModel.flagUrl, emptyCountryEntity.flags?.url)
        assertEquals(emptyCountryModel.flagDescription, emptyCountryEntity.flags?.description)
        assertEquals(emptyCountryModel.population, emptyCountryEntity.population)
    }
}
