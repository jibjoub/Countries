package com.example.data.db

import androidx.room.Embedded
import androidx.room.Relation

data class CountryWithCapitals(
    @Embedded val country: CountryEntityDb,
    @Relation(
        parentColumn = "name",
        entityColumn = "countryName",
    )
    val capitals: List<CapitalEntityDb>,
)
