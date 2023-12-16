package com.example.l4etwandroid.presentation.auth.login.models

sealed class LoginAction {
    data object OpenMainFlow : LoginAction()
}