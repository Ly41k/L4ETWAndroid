package com.example.l4etwandroid.core.utils

import android.content.Context
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.example.l4etwandroid.core.di.PlatformConfiguration
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.data.auth.setting.SettingsAuthDataSource
import com.example.l4etwandroid.domain.CountryItem
import com.example.l4etwandroid.domain.ProfileItem
import com.example.l4etwandroid.domain.TaskItem

interface LogoutHelper {
    fun logout()
}

class LogoutHelperImpl(
    private val taskStore: ClearableBaseStore<List<TaskItem>>,
    private val profileStore: ClearableBaseStore<ProfileItem>,
    private val countStore: ClearableBaseStore<List<CountryItem>>,
    private val platformConfiguration: PlatformConfiguration,
    private val settingsAuthDataSource: SettingsAuthDataSource
) : LogoutHelper {
    override fun logout() {
        taskStore.clear()
        profileStore.clear()
        countStore.clear()
        settingsAuthDataSource.clearToken()
        clearImageCache(platformConfiguration.androidContext)
    }

    @OptIn(ExperimentalCoilApi::class)
    private fun clearImageCache(context: Context) {
        context.imageLoader.diskCache?.clear()
        context.imageLoader.memoryCache?.clear()
    }

}

