package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface CountryDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertCountry(country: CountryEntityDb)

    @Insert(onConflict = REPLACE)
    suspend fun insertCapital(capital: CapitalEntityDb)

    @Query("DELETE FROM countries")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteCountry(country: CountryEntityDb)
}
