package com.example.countries

import android.app.Application
import com.example.data.remote.api.CountryApi
import com.example.data.repository.WorldRepositoryImpl
import com.example.domain.repository.WorldRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class CountriesApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

// TODO know where in the architecture to put these create separate files
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideCountryApi(retrofit: Retrofit): CountryApi = retrofit.create(CountryApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindWorldRepository(worldRepositoryImpl: WorldRepositoryImpl): WorldRepository
}
