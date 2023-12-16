package com.example.l4etwandroid.compose.auth.login

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.example.l4etwandroid.core.navigation.NavigationTree
import com.example.l4etwandroid.presentation.auth.login.LoginViewModel
import com.example.l4etwandroid.presentation.auth.login.models.LoginAction
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag

@Composable
fun LoginScreen() {

    val rootController = LocalRootController.current

    StoredViewModel(factory = { LoginViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()

        LoginView(state = state.value) { viewModel.obtainEvent(it) }

        when (action.value) {
            is LoginAction.OpenMainFlow -> {
                rootController.findRootController().present(
                    screen = NavigationTree.Main.TasksScreen.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
            }

            null -> {}
        }
    }
}