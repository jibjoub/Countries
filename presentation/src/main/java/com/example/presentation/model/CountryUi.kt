package com.example.presentation.model

import com.example.domain.model.CountryModel

data class CountryUi (
    val id: String,
    val commonName: String,
    val officialName: String,
    val continents: String,
    val flagUrl: String,
    val flagDescription: String,
    val population: String
) {
    companion object {
        fun mapToUiModel(countryModel: CountryModel): CountryUi {
            return CountryUi(
                countryModel.id,
                countryModel.commonName,
                countryModel.officialName,
                countryModel.continents.toString(),
                countryModel.flagUrl,
                countryModel.flagDescription,
                countryModel.population.toString()
            )
        }
    }
}