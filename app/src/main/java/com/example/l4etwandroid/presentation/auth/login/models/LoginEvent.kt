package com.example.l4etwandroid.presentation.auth.login.models

sealed class LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    data object PasswordShowClick : LoginEvent()
    data object LoginClick : LoginEvent()
}