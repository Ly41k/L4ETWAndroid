package com.example.l4etwandroid.compose.auth.login.models

data class LoginViewState (
    val email: String,
    val password: String,
    val isSending: Boolean = false,
    val passwordHidden: Boolean = true
)