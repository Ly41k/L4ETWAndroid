package com.example.l4etwandroid.data.task.ktor

import com.example.l4etwandroid.api.task.models.TaskResponse
import com.example.l4etwandroid.data.auth.setting.SettingsAuthDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.path

class KtorTaskRemoteDataSource(private val httpClient: HttpClient, private val settings: SettingsAuthDataSource) {

    suspend fun performGetTasks(): List<TaskResponse> {
        return httpClient.get {
            header("Authentication-token", settings.fetchToken())
            url { path("api/task") }
        }.body()
    }
}