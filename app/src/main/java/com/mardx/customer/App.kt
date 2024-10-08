package com.mardx.customer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
//import org.koin.ksp.generated.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf())
        }
    }
}