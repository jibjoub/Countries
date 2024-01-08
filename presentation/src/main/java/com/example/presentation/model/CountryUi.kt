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
        fun mapToUiModel(countryModel: CountryModel) =
            CountryUi(
                id = countryModel.id,
                commonName = countryModel.commonName,
                officialName = countryModel.officialName,
                continents = countryModel.continents?.joinToString(", ") ?: "Not defined",
                flagUrl = countryModel.flagUrl,
                flagDescription = countryModel.flagDescription,
                population = countryModel.population?.let { "${it.div(1000000)}M" } ?: "Not defined",
            )
    }
}
