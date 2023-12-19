package com.example.l4etwandroid.presentation

import app.cash.turbine.test
import com.example.l4etwandroid.api.auth.AuthRepository
import com.example.l4etwandroid.domain.AuthItem
import com.example.l4etwandroid.presentation.auth.login.LoginViewModel
import com.example.l4etwandroid.presentation.auth.login.models.LoginEvent
import com.example.l4etwandroid.presentation.auth.login.models.LoginViewState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions

class LoginViewModelTest {

    private val standardTestDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: LoginViewModel

    private val authRepository = mockk<AuthRepository> {
        coEvery { (login(any(), any())) } returns mockk<AuthItem>()
        every { isUserLoggedIn() } returns true
        every { fetchToken() } returns "Token"
    }

    private fun initViewModel() {
        viewModel = LoginViewModel(authRepository)
    }

    @org.junit.Test
    fun `When the user text an email we call LoginEvent_EmailChanged`() = runTest(standardTestDispatcher) {
        val testEmail = "email@dot.com"
        initViewModel()
        viewModel.obtainEvent(LoginEvent.EmailChanged(testEmail))
        viewModel.viewStates().test {
            Assertions.assertEquals(LoginViewState(email = testEmail, password = ""), awaitItem())
        }
    }
}