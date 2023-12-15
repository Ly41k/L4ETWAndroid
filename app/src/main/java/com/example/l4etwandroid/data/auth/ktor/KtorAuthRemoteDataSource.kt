package com.example.l4etwandroid.data.auth.ktor

import com.example.l4etwandroid.api.auth.models.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.path

class KtorAuthRemoteDataSource(private val httpClient: HttpClient) {

    suspend fun performLogin(login: String, password: String): LoginResponse {
        return httpClient.post {
            url {
                path("login")
                parameter("login", login)
                parameter("password", password)
            }
        }.body()
    }
}