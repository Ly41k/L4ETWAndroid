package com.example.l4etwandroid.api.auth.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(@SerialName("token") val token: String)