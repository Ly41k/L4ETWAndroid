package com.example.l4etwandroid.compose.auth.login.models

sealed class LoginAction {
    data object OpenMainFlow : LoginAction()
}