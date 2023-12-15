package com.example.l4etwandroid.data.auth

import com.example.l4etwandroid.api.auth.AuthRepository
import com.example.l4etwandroid.data.auth.ktor.KtorAuthRemoteDataSource
import com.example.l4etwandroid.data.auth.setting.SettingsAuthDataSource
import com.example.l4etwandroid.domain.AuthItem

class AuthRepositoryImpl(
    private val remoteDataSource: KtorAuthRemoteDataSource,
    private val cacheDataSource: SettingsAuthDataSource
) : AuthRepository {
    override suspend fun login(login: String, password: String): AuthItem {
        val response = remoteDataSource.performLogin(login = login, password = password)
        cacheDataSource.saveToken(response.token)
        return AuthItem(response.token)
    }

    override fun isUserLoggedIn(): Boolean {
        return cacheDataSource.fetchToken().isNotBlank()
    }

    override fun fetchToken(): String {
        return cacheDataSource.fetchToken()
    }
}