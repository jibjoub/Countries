package com.example.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity
data class CountryWithContinents(
    @Embedded val country: CountryEntityDb,
    @Relation(
        parentColumn = "country_name",
        entityColumn = "continent_name",
        associateBy = Junction(CountryContinentCrossRef::class),
    )
    val continents: List<ContinentEntityDb>,
)
