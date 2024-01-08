package com.example.presentation.model

import com.example.domain.model.CountryModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CountryUiTest {
    @Test
    fun `Given a CountryModel with a list of 2 continents, when mapping it to CountryUi, then the different continents should be separated by a comma and a space`() {
        val multipleContinentsCountryModel = countryModelBuilder(continents = listOf("Europe", "Asia"))
        val countryUi = CountryUi.mapToUiModel(multipleContinentsCountryModel)

        assertEquals("Europe, Asia", countryUi.continents)
    }

    @Test
    fun `Given a CountryModel with null value of continents, when mapping it to CountryUi, then the continent value should be Not defined`() {
        val populationCountryModel = countryModelBuilder(continents = null)
        val countryUi = CountryUi.mapToUiModel(populationCountryModel)

        assertEquals("Not defined", countryUi.continents)
    }

    @Test
    fun `Given a CountryModel with a valid value of population, when mapping it to CountryUi, then the population should be written in millions`() {
        val populationCountryModel = countryModelBuilder(population = 25_000_000)
        val countryUi = CountryUi.mapToUiModel(populationCountryModel)

        assertEquals(countryUi.population, "25M")
    }

    @Test
    fun `Given a CountryModel with null value of population, when mapping it to CountryUi, then the continent value should be Not defined`() {
        val populationCountryModel = countryModelBuilder(population = null)
        val countryUi = CountryUi.mapToUiModel(populationCountryModel)

        assertEquals("Not defined", countryUi.population)
    }

    private fun countryModelBuilder(
        id: String = "test",
        commonName: String = "test",
        officialName: String = "test",
        continents: List<String>? = listOf("test"),
        flagUrl: String = "test",
        flagDescription: String = "test",
        population: Int? = 1,
    ) = CountryModel(
        id,
        commonName,
        officialName,
        continents,
        flagUrl,
        flagDescription,
        population,
    )
}
