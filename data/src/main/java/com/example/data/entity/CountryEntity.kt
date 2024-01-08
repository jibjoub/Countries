package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class CountryEntity(
    @SerializedName("cca3") val id: String?,
    val name: NameEntity?,
    val continents: List<String>?,
    val flags: FlagEntity?,
    val population: Int?,
)
