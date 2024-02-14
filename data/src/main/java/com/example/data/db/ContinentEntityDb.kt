package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "continent")
data class ContinentEntityDb(
    @PrimaryKey
    @ColumnInfo(name = "continent_name") val continentName: String,
)
