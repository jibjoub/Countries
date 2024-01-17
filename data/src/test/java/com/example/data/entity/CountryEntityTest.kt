package com.example.data.entity

import junit.framework.TestCase
import org.junit.Test

class CountryEntityTest {
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
        val emptyCountryModel = emptyCountryEntity.mapToCountryModel()

        TestCase.assertEquals(emptyCountryModel.id, emptyCountryEntity.id)
        TestCase.assertEquals(emptyCountryModel.commonName, emptyCountryEntity.name?.common)
        TestCase.assertEquals(emptyCountryModel.continents, emptyCountryEntity.continents)
        TestCase.assertEquals(emptyCountryModel.flagUrl, emptyCountryEntity.flags?.url)
        TestCase.assertEquals(
            emptyCountryModel.flagDescription,
            emptyCountryEntity.flags?.description,
        )
        TestCase.assertEquals(emptyCountryModel.population, emptyCountryEntity.population)
    }
}
