package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["country_name", "continent_name"]) // Shouldn't it be countryId?
data class CountryContinentCrossRef(
    @ColumnInfo(name = "country_name") val countryName: String,
    @ColumnInfo(name = "continent_name") val continentName: String,
)
