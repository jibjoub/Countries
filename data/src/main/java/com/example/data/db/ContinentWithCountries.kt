package com.example.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity
data class ContinentWithCountries(
    @Embedded val continent: ContinentEntityDb,
    @Relation(
        parentColumn = "continent_name",
        entityColumn = "country_name",
        associateBy = Junction(CountryContinentCrossRef::class),
    )
    val countries: List<CountryEntityDb>,
)
