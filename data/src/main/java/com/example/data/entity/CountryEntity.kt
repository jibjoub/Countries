package com.example.data.entity

data class CountryEntity(
    val id: String,
    val name: NameEntity,
    val continents: List<String>,
    val flags: FlagEntity,
    val population: Int
)