package com.example.domain.model

data class CountryModel(
    val id: String?,
    val commonName: String?,
    val officialName: String?,
    val capitals: List<String>?,
    val continents: List<String>?,
    val flagUrl: String?,
    val flagDescription: String?,
    val population: Int?,
)
