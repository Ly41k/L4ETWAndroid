package com.example.l4etwandroid.data.auth

import com.example.l4etwandroid.api.auth.AuthRepository
import com.example.l4etwandroid.data.auth.ktor.KtorAuthRemoteDataSource
import com.example.l4etwandroid.data.auth.setting.SettingsAuthDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val authModule = DI.Module("authModule") {
    bind<AuthRepository>() with singleton {
        AuthRepositoryImpl(instance(), instance())
    }

    bind<SettingsAuthDataSource>() with provider {
        SettingsAuthDataSource(instance())
    }

    bind<KtorAuthRemoteDataSource>() with provider {
        KtorAuthRemoteDataSource(instance())
    }
}