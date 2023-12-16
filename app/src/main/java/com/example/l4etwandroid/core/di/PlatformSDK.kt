package com.example.l4etwandroid.core.di

import com.example.l4etwandroid.core.ktor.ktorModule
import com.example.l4etwandroid.core.settings.settingsModule
import com.example.l4etwandroid.data.auth.authModule
import com.example.l4etwandroid.data.task.taskModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton

object PlatformSDK {

    fun init(configuration: PlatformConfiguration) {
        val coreModule = DI.Module(
            name = "coreModule",
            init = { bind<PlatformConfiguration>() with singleton { configuration } }
        )

        Inject.createDependencies(
            DI {
                importAll(
                    coreModule,
                    ktorModule,
                    authModule,
                    settingsModule,
                    taskModule
                )
            }.direct
        )
    }
}