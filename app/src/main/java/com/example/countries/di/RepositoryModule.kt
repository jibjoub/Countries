package com.example.countries.di

import com.example.data.repository.WorldRepositoryImpl
import com.example.domain.repository.WorldRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindWorldRepository(worldRepositoryImpl: WorldRepositoryImpl): WorldRepository
}
