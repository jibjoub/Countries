package com.example.countries.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.AppDatabase
import com.example.data.db.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideCountryDao(appDatabase: AppDatabase): CountryDao {
        return appDatabase.countryDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "CountryDatabase",
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
