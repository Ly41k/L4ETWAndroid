package com.example.l4etwandroid.presentation.auth.login

import com.adeo.kviewmodel.BaseSharedViewModel
import com.example.l4etwandroid.api.auth.AuthRepository
import com.example.l4etwandroid.core.di.Inject
import com.example.l4etwandroid.presentation.auth.login.models.LoginAction
import com.example.l4etwandroid.presentation.auth.login.models.LoginEvent
import com.example.l4etwandroid.presentation.auth.login.models.LoginViewState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = Inject.instance()
) :
    BaseSharedViewModel<LoginViewState, LoginAction, LoginEvent>(
        initialState = LoginViewState(email = "", password = "")
    ) {

    init {
        checkUserLoggedIn()
    }

    override fun obtainEvent(viewEvent: LoginEvent) {
        println("Event coming: $viewEvent")
        when (viewEvent) {
            is LoginEvent.LoginClick -> sendLogin()
            is LoginEvent.EmailChanged -> obtainEmailChanged(viewEvent.value)
            is LoginEvent.PasswordChanged -> obtainPasswordChanged(viewEvent.value)
            LoginEvent.PasswordShowClick -> changePasswordVisibility()
        }
    }

    private fun checkUserLoggedIn() {
        if (authRepository.isUserLoggedIn()) {
            viewAction = LoginAction.OpenMainFlow
        }
    }

    private fun changePasswordVisibility() {
        viewState = viewState.copy(passwordHidden = !viewState.passwordHidden)
    }

    private fun sendLogin() {
        viewState = viewState.copy(isSending = true)
        viewModelScope.launch {
            try {
                val response = authRepository.login(viewState.email, viewState.password)
                if (response.token.isNotBlank()) {
                    viewState = viewState.copy(email = "", password = "")
                    viewAction = LoginAction.OpenMainFlow
                } else {
                    viewState = viewState.copy(isSending = false)
                }
            } catch (e: Exception) {
                viewState = viewState.copy(isSending = false)
            }
        }
    }

    private fun obtainEmailChanged(value: String) {
        viewState = viewState.copy(email = value)
    }

    private fun obtainPasswordChanged(value: String) {
        viewState = viewState.copy(password = value)
    }
}