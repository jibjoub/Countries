package com.example.presentation.model

import com.example.domain.model.CountryModel
import java.math.BigDecimal
import java.math.RoundingMode

data class CountryUi(
    val id: String,
    val commonName: String,
    val officialName: String,
    val capitals: String,
    val continents: String,
    val flagUrl: String,
    val flagDescription: String,
    val population: String,
) {
    companion object {
        fun mapToCountryUi(countryModel: CountryModel) =
            CountryUi(
                id = countryModel.id ?: "Not defined",
                commonName = countryModel.commonName ?: "Not defined",
                officialName = countryModel.officialName ?: "Not defined",
                capitals = itemSeparator(countryModel.capitals),
                continents = itemSeparator(countryModel.continents),
                flagUrl = countryModel.flagUrl ?: "Not defined",
                flagDescription = countryModel.flagDescription ?: "Not defined",
                population = populationParser(countryModel.population),
            )

        private fun itemSeparator(items: List<String>?): String {
            return items?.joinToString(", ") ?: "Not defined"
        }

        private fun populationParser(population: Int?): String {
            return when {
                population == null -> "Not defined"
                population < 1_000 -> population.toString()
                population < 1_000_000 -> "${divideAndRound(population, 1_000, 0)}K"
                population < 1_000_000_000 -> "${divideAndRound(population, 1_000_000, 0)}M"
                else -> "${divideAndRound(population, 1_000_000_000, 3)}B"
            }
        }

        private fun divideAndRound(
            population: Int,
            divisor: Int,
            decimals: Int,
        ): BigDecimal {
            return BigDecimal(population.toDouble() / divisor).setScale(decimals, RoundingMode.HALF_EVEN)
        }
    }
}
