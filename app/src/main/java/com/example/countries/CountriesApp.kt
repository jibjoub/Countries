package com.example.countries

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CountriesApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
