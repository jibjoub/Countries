package com.example.presentation.model

import com.example.domain.model.CountryModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CountryUiTest {
    @Test
    fun `Given a CountryModel with a list of 2 continents, when mapping it to CountryUi, then the different continents should be separated by a comma and a space`() {
        val multipleContinentsCountryModel = countryModelBuilder(continents = listOf("Europe", "Asia"))
        val countryUi = CountryUi.mapToCountryUi(multipleContinentsCountryModel)

        assertEquals("Europe, Asia", countryUi.continents)
    }

    @Test
    fun `Given a CountryModel with null value of continents, when mapping it to CountryUi, then the continent value should be Not defined`() {
        val populationCountryModel = countryModelBuilder(continents = null)
        val countryUi = CountryUi.mapToCountryUi(populationCountryModel)

        assertEquals("Not defined", countryUi.continents)
    }

    @Test
    fun `Given a CountryModel with a list of 2 capitals, when mapping it to CountryUi, then the different capitals should be separated by a comma and a space`() {
        val multipleCapitalsCountryModel =
            countryModelBuilder(
                commonName = "Chile",
                capitals = listOf("Santiago", "Valparaiso"),
            )
        val countryUi = CountryUi.mapToCountryUi(multipleCapitalsCountryModel)

        assertEquals("Santiago, Valparaiso", countryUi.capitals)
    }

    @Test
    fun `Given a CountryModel with a valid value of population, when mapping it to CountryUi, then the population should be written in millions`() {
        val populationCountryModel = countryModelBuilder(population = 25_000_000)
        val countryUi = CountryUi.mapToCountryUi(populationCountryModel)

        assertEquals(countryUi.population, "25M")
    }

    @Test
    fun `Given a CountryModel with null value of population, when mapping it to CountryUi, then the continent value should be Not defined`() {
        val nullPopulationCountryModel = countryModelBuilder()
        val countryUi = CountryUi.mapToCountryUi(nullPopulationCountryModel)

        assertEquals("Not defined", countryUi.population)
    }

    private fun countryModelBuilder(
        id: String? = null,
        commonName: String? = null,
        officialName: String? = null,
        capitals: List<String>? = null,
        continents: List<String>? = null,
        flagUrl: String? = null,
        flagDescription: String? = null,
        population: Int? = null,
    ) = CountryModel(
        id,
        commonName,
        officialName,
        capitals,
        continents,
        flagUrl,
        flagDescription,
        population,
    )
}
