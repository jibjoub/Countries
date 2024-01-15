package com.example.countries

import android.app.Application
import com.example.data.remote.api.RetrofitInstance
import com.example.data.repository.WorldRepositoryImpl
import com.example.domain.repository.WorldRepository
import com.example.domain.usecase.GetCountryByIdUseCase
import com.example.domain.usecase.GetWorldCountriesUseCase
import com.example.presentation.viewmodel.DetailViewModel
import com.example.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class CountriesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule =
            module {
                single { RetrofitInstance }

                single<WorldRepository> {
                    WorldRepositoryImpl(get())
                }

                single {
                    GetWorldCountriesUseCase(get())
                }
                single {
                    GetCountryByIdUseCase(get())
                }

                single { MainViewModel(get()) }
                single { DetailViewModel(get()) }
            }

        startKoin {
            androidContext(this@CountriesApp)
            modules(appModule)
        }
    }
}
