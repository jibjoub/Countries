package com.example.presentation.model

import com.example.domain.model.CountryModel
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CountryUiTest {
    @Test
    fun `When mapping a CountryModel to CountryUi, the different continents should be separated by a comma and a space`() {
        val multipleContinentsCountryModel = CountryModel("test", "test", "test", listOf("Europe", "Asia"), "test", "test", 1)
        val countryUi = CountryUi.mapToUiModel(multipleContinentsCountryModel)

        assertEquals(countryUi.continents, "Europe, Asia")
    }

    @Test
    fun `When mapping a CountryModel to CountryUi, the population should be written in millions`() {
        val populationCountryModel = CountryModel("test", "test", "test", listOf("Europe", "Asia"), "test", "test", 25000000)
        val countryUi = CountryUi.mapToUiModel(populationCountryModel)

        assertEquals(countryUi.population, "25M")
    }
}