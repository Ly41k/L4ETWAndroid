package com.example.l4etwandroid.presentation.main.task

import android.util.Log
import com.adeo.kviewmodel.BaseSharedViewModel
import com.example.l4etwandroid.api.task.TaskRepository
import com.example.l4etwandroid.core.di.Inject
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.domain.TaskItem
import com.example.l4etwandroid.presentation.main.task.models.TaskAction
import com.example.l4etwandroid.presentation.main.task.models.TaskEvent
import com.example.l4etwandroid.presentation.main.task.models.TaskViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel : BaseSharedViewModel<TaskViewState, TaskAction, TaskEvent>(
    initialState = TaskViewState(
        tasks = emptyList()
    )
) {

    private val taskStore: ClearableBaseStore<List<TaskItem>> = Inject.instance()
    private val taskRepository: TaskRepository = Inject.instance()

    init {
        taskStore.observe().onEach {
            viewState = viewState.copy(tasks = it)
        }.launchIn(viewModelScope)

        viewModelScope.launch {
            taskRepository.getTasks()
        }
    }

    override fun obtainEvent(viewEvent: TaskEvent) {
        Log.d("TESTING_TAG", "obtainEvent - $viewEvent")
    }
}