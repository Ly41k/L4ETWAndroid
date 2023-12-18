package com.example.l4etwandroid.data.profile.ktor

import com.example.l4etwandroid.api.profile.models.ProfileRequest
import com.example.l4etwandroid.api.profile.models.ProfileResponse
import com.example.l4etwandroid.data.auth.setting.SettingsAuthDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.path

class KtorProfileRemoteDataSource(private val httpClient: HttpClient, private val settings: SettingsAuthDataSource) {

    suspend fun performGetProfile(): ProfileResponse {
        return httpClient.get {
            header("Authentication-token", settings.fetchToken())
            url { path("api/profile") }
        }.body()
    }

    suspend fun performUpdateProfile(request: ProfileRequest) {
        return httpClient.put {
            header("Authentication-token", settings.fetchToken())
            url {
                path("api/profile")
                setBody(request)
            }
        }.body()
    }
}