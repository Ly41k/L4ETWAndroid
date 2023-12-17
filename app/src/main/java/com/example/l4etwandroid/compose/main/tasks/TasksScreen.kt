package com.example.l4etwandroid.compose.main.tasks

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.example.l4etwandroid.core.navigation.NavigationTree
import com.example.l4etwandroid.presentation.main.task.TaskViewModel
import com.example.l4etwandroid.presentation.main.task.models.TaskAction
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun TasksScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { TaskViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState().value
        val action = viewModel.viewActions().observeAsState().value

        TasksView(state = state) { viewModel.obtainEvent(it) }

        when (action) {

            TaskAction.OpenCreateTask -> {
                rootController.present(screen = NavigationTree.Main.AddOrEditTaskScreen.name, params = null)
                viewModel.clearAction()
            }

            is TaskAction.OpenEditTask -> {
                rootController.present(screen = NavigationTree.Main.AddOrEditTaskScreen.name, params = action.taskId)
                viewModel.clearAction()
            }

            null -> {}
        }
    }
}