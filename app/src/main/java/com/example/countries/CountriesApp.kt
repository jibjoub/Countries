package com.example.countries

import android.app.Application
import com.example.data.remote.api.RetrofitInstance
import com.example.data.repository.CountryRepositoryImpl
import com.example.domain.FetchCountriesUseCase
import com.example.domain.repository.CountryRepository
import com.example.presentation.viewmodel.CountriesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class CountriesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {

            viewModel { CountriesViewModel(get()) }

            single {
                FetchCountriesUseCase(get())
            }

            single<CountryRepository> {
                CountryRepositoryImpl(get())
            }

            single { RetrofitInstance }
        }

        startKoin {
            androidContext(this@CountriesApp)
            modules(appModule)
        }
    }
}