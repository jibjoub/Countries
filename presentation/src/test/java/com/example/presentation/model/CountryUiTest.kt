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
                name = "Chile",
                capitals = listOf("Santiago", "Valparaiso"),
            )
        val countryUi = CountryUi.mapToCountryUi(multipleCapitalsCountryModel)

        assertEquals("Santiago, Valparaiso", countryUi.capitals)
    }

    @Test
    fun `Given a CountryModel with a valid value of population, when mapping it to CountryUi, then the population should be written in a specific way`() {
        val billionsCountryModel = countryModelBuilder(population = 1_380_743_321)
        val billionsCountryUi = CountryUi.mapToCountryUi(billionsCountryModel)

        val millionsCountryModel = countryModelBuilder(population = 25_937_764)
        val millionsCountryUi = CountryUi.mapToCountryUi(millionsCountryModel)

        val thousandsCountryModel = countryModelBuilder(population = 250_521)
        val thousandsCountryUi = CountryUi.mapToCountryUi(thousandsCountryModel)

        val tinyCountryModel = countryModelBuilder(population = 500)
        val tinyCountryUi = CountryUi.mapToCountryUi(tinyCountryModel)

        assertEquals("1.381B", billionsCountryUi.population)
        assertEquals("26M", millionsCountryUi.population)
        assertEquals("251K", thousandsCountryUi.population)
        assertEquals("500", tinyCountryUi.population)
    }

    @Test
    fun `Given a CountryModel with null value of population, when mapping it to CountryUi, then the continent value should be Not defined`() {
        val nullPopulationCountryModel = countryModelBuilder()
        val countryUi = CountryUi.mapToCountryUi(nullPopulationCountryModel)

        assertEquals("Not defined", countryUi.population)
    }

    private fun countryModelBuilder(
        id: String? = null,
        name: String? = null,
        capitals: List<String>? = null,
        continents: List<String>? = null,
        flagUrl: String? = null,
        flagDescription: String? = null,
        population: Int? = null,
    ) = CountryModel(
        id,
        name,
        capitals,
        continents,
        flagUrl,
        flagDescription,
        population,
    )
}
