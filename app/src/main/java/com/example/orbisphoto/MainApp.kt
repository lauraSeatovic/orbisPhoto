package com.example.orbisphoto

import android.app.Application
import com.example.tmdb.modules.appModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}