package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CountryEntityDb::class, CapitalEntityDb::class, ContinentEntityDb::class, CountryContinentCrossRef::class],
    version = 4,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
