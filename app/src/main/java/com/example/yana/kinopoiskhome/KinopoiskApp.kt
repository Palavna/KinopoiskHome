package com.example.yana.kinopoiskhome

import android.app.Application
import com.example.yana.kinopoiskhome.di.kinopoiskModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KinopoiskApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@KinopoiskApp)
            kinopoiskModules
        }
    }
}