package com.example.presentation.model

import com.example.domain.model.CountryModel

data class CountryUi(
    val id: String,
    val commonName: String,
    val officialName: String,
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
                continents = countryModel.continents?.joinToString(", ") ?: "Not defined",
                flagUrl = countryModel.flagUrl ?: "Not defined",
                flagDescription = countryModel.flagDescription ?: "Not defined",
                population = countryModel.population?.let { "${it.div(1000000)}M" } ?: "Not defined",
            )
    }
}
