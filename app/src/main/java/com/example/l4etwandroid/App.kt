package com.example.l4etwandroid

import android.app.Application
import com.example.l4etwandroid.core.di.PlatformConfiguration
import com.example.l4etwandroid.core.di.PlatformSDK

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PlatformSDK.init(
            PlatformConfiguration(androidContext = applicationContext)
        )
    }
}