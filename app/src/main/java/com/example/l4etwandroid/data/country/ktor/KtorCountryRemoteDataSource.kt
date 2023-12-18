package com.example.l4etwandroid.data.country.ktor

import com.example.l4etwandroid.api.country.models.CountryResponse
import com.example.l4etwandroid.data.auth.setting.SettingsAuthDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.path

class KtorCountryRemoteDataSource(private val httpClient: HttpClient, private val settings: SettingsAuthDataSource) {
    suspend fun performGetCountries(): List<CountryResponse> {
        return httpClient.get {
            header("Authentication-token", settings.fetchToken())
            url { path("api/countries") }
        }.body()
    }
}