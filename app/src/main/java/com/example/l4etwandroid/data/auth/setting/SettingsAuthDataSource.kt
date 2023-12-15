package com.example.l4etwandroid.data.auth.setting

import com.example.l4etwandroid.core.utils.Constants.TOKEN_KEY
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class SettingsAuthDataSource(private val settings: Settings) {

    fun saveToken(token: String) {
        settings.putString(TOKEN_KEY, token)

    }

    fun fetchToken(): String = settings[TOKEN_KEY, ""]
}