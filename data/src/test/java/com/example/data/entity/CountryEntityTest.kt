package com.example.data.entity

import com.example.data.remote.entity.CountryEntity
import com.example.data.remote.entity.FlagEntity
import com.example.data.remote.entity.NameEntity
import com.example.data.remote.entity.mapToCountryModel
import com.example.domain.model.CountryModel
import junit.framework.TestCase
import org.junit.Test

class CountryEntityTest {
    @Test
    fun `Given a CountryEntity, when mapping to CountryModel, the fields should be correctly mapped`() {
        val countryEntity =
            CountryEntity(
                "EST",
                NameEntity("Estonia", "Republic of Estonia"),
                listOf("Tallinn"),
                listOf("Europe"),
                FlagEntity("test", "test"),
                1_331_057,
            )

        val countryModelExpected =
            CountryModel(
                countryEntity.id,
                countryEntity.name?.common,
                countryEntity.capitals,
                countryEntity.continents,
                countryEntity.flags?.url,
                countryEntity.flags?.description,
                countryEntity.population,
            )
        val countryModel = countryEntity.mapToCountryModel()

        TestCase.assertEquals(countryModelExpected, countryModel)
    }
}
