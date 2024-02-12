package com.example.data.remote.entity

import com.example.domain.model.CountryModel
import com.google.gson.annotations.SerializedName

data class CountryEntity(
    @SerializedName("cca3") val id: String?,
    val name: NameEntity?,
    @SerializedName("capital") val capitals: List<String>?,
    val continents: List<String>?,
    val flags: FlagEntity?,
    val population: Int?,
)

fun CountryEntity.mapToCountryModel(): CountryModel {
    return CountryModel(
        id = this.id,
        name = this.name?.common,
        capitals = this.capitals,
        continents = this.continents,
        flagUrl = this.flags?.url,
        flagDescription = this.flags?.description,
        population = this.population,
    )
}
