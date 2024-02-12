package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntityDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String?,
    val capitals: String?,
    val continents: String?,
    @ColumnInfo(name = "flag_url") val flagUrl: String?,
    @ColumnInfo(name = "flag_description") val flagDescription: String?,
    val population: String?,
)
