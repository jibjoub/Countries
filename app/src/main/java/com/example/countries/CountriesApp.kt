package com.example.countries

import android.app.Application
import com.example.data.remote.api.CountryApi
import com.example.data.repository.WorldRepositoryImpl
import com.example.domain.repository.WorldRepository
import com.example.domain.usecase.GetCountryByIdUseCase
import com.example.domain.usecase.GetWorldCountriesUseCase
import com.example.presentation.viewmodel.DetailViewModel
import com.example.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule =
            module {
                single<CountryApi> {
                    get<Retrofit>().create(CountryApi::class.java)
                }

                single {
                    Retrofit.Builder()
                        .baseUrl("https://restcountries.com/v3.1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

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
                factory { DetailViewModel(get()) }
            }

        startKoin {
            androidContext(this@CountriesApp)
            modules(appModule)
        }
    }
}
