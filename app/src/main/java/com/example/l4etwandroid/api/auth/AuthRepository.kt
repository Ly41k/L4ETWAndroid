package com.example.l4etwandroid.api.auth

import com.example.l4etwandroid.domain.AuthItem

interface AuthRepository {
    suspend fun login(login: String, password: String): AuthItem
    fun isUserLoggedIn(): Boolean
    fun fetchToken(): String
}