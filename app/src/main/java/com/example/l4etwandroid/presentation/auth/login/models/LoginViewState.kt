package com.example.l4etwandroid.presentation.auth.login.models

data class LoginViewState(
    val email: String,
    val password: String,
    val isSending: Boolean = false,
    val passwordHidden: Boolean = true
)