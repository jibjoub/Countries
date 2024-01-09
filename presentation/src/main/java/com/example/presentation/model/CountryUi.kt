package com.example.presentation.model

import com.example.domain.model.CountryModel

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
                population = countryModel.population?.let { "${it.div(1000000)}M" } ?: "Not defined",
            )

        private fun itemSeparator(items: List<String>?): String {
            return items?.joinToString(", ") ?: "Not defined"
        }
    }
}
