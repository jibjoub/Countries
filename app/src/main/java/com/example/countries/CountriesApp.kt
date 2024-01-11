package com.example.countries

import android.app.Application
import com.example.data.remote.api.RetrofitInstance
import com.example.data.repository.WorldRepositoryImpl
import com.example.domain.repository.WorldRepository
import com.example.domain.usecase.FetchCountryByIdUseCase
import com.example.domain.usecase.FetchWorldCountriesUseCase
import com.example.presentation.viewmodel.CountriesViewModel
import com.example.presentation.viewmodel.CountryDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class CountriesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule =
            module {
                viewModel { CountriesViewModel(get()) }
                viewModel { CountryDetailsViewModel(get()) }

                single {
                    FetchWorldCountriesUseCase(get())
                }
                single {
                    FetchCountryByIdUseCase(get())
                }

                single<WorldRepository> {
                    WorldRepositoryImpl(get())
                }

                single { RetrofitInstance }
            }

        startKoin {
            androidContext(this@CountriesApp)
            modules(appModule)
        }
    }
}
