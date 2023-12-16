package com.example.l4etwandroid.compose.main.tasks

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.example.l4etwandroid.presentation.main.task.TaskViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun TasksScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { TaskViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        val action = viewModel.viewActions().observeAsState()

        TasksView(state = state.value) { viewModel.obtainEvent(it) }

        when (action.value) {
            null -> {}
        }
    }
}