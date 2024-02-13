package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountryEntityDb::class, CapitalEntityDb::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
