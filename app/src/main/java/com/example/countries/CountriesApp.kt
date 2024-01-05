package com.example.countries

import android.app.Application
import com.example.data.remote.api.ApiService
import com.example.data.remote.api.ApiServiceImpl
import com.example.data.remote.api.RetrofitInstance
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class CountriesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {

            single { RetrofitInstance }

            single<ApiService> {
                ApiServiceImpl(get())
            }
        }

        startKoin {
            androidContext(this@CountriesApp)
            modules(appModule)
        }
    }
}