package com.example.l4etwandroid.compose.main.addoredittask

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.example.l4etwandroid.presentation.main.addoredittask.AddOrEditViewModel
import com.example.l4etwandroid.presentation.main.addoredittask.models.AddOrEditAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun AddOrEditScreen(taskId: Long? = null) {

    val rootController = LocalRootController.current

    StoredViewModel(factory = { AddOrEditViewModel(taskId) }) { viewModel ->
        val state = viewModel.viewStates().observeAsState().value
        val action = viewModel.viewActions().observeAsState().value

        AddOrEditView(state = state) { viewModel.obtainEvent(it) }

        when (action) {
            AddOrEditAction.Back -> rootController.popBackStack()
            null -> {}
        }
    }
}