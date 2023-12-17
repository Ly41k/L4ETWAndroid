package com.example.l4etwandroid.data.task.ktor

import com.example.l4etwandroid.api.task.models.TaskRequest
import com.example.l4etwandroid.api.task.models.TaskResponse
import com.example.l4etwandroid.data.auth.setting.SettingsAuthDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.path

class KtorTaskRemoteDataSource(private val httpClient: HttpClient, private val settings: SettingsAuthDataSource) {

    suspend fun performGetTasks(): List<TaskResponse> {
        return httpClient.get {
            header("Authentication-token", settings.fetchToken())
            url { path("api/task") }
        }.body()
    }

    suspend fun performAddTask(request: TaskRequest): TaskResponse {
        return httpClient.post {
            header("Authentication-token", settings.fetchToken())
            url {
                path("api/task")
                setBody(request)
            }
        }.body()
    }


    suspend fun performEditTask(request: TaskRequest) {
        return httpClient.put {
            header("Authentication-token", settings.fetchToken())
            url {
                path("api/task/${request.id}")
                setBody(request)
            }
        }.body()
    }

    suspend fun performDeleteTask(taskId: Long) {
        return httpClient.delete {
            header("Authentication-token", settings.fetchToken())
            url { path("api/task/$taskId") }
        }.body()
    }
}