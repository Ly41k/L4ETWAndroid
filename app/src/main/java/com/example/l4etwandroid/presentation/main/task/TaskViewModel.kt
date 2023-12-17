package com.example.l4etwandroid.presentation.main.task

import com.adeo.kviewmodel.BaseSharedViewModel
import com.example.l4etwandroid.api.task.TaskRepository
import com.example.l4etwandroid.core.di.Inject
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.core.utils.formatWithPattern
import com.example.l4etwandroid.domain.TaskItem
import com.example.l4etwandroid.presentation.main.task.models.TaskAction
import com.example.l4etwandroid.presentation.main.task.models.TaskEvent
import com.example.l4etwandroid.presentation.main.task.models.TaskViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel : BaseSharedViewModel<TaskViewState, TaskAction, TaskEvent>(
    initialState = TaskViewState(
        tasks = emptyList()
    )
) {

    private val taskStore: ClearableBaseStore<List<TaskItem>> = Inject.instance()
    private val taskRepository: TaskRepository = Inject.instance()

    private val _isTitleSorting = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val isTitleSorting: SharedFlow<Unit> = _isTitleSorting

    init {

        taskStore.observe()
            .combine(isTitleSorting) { tasks, _ -> tasks }
            .map { tasks ->
                tasks.sortedBy { task ->
                    if (viewState.isTitleSorting) {
                        task.title.uppercase()
                    } else {
                        task.date.formatWithPattern()
                    }
                }
            }
            .onEach { viewState = viewState.copy(tasks = it) }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)

        viewModelScope.launch(Dispatchers.IO) {
            viewState = viewState.copy(isSending = true)
            viewState = try {
                taskRepository.getTasks()
                viewState.copy(isSending = false)
            } catch (e: Exception) {
                viewState.copy(isSending = false)
            }
        }

        _isTitleSorting.tryEmit(Unit)
    }

    override fun obtainEvent(viewEvent: TaskEvent) {
        println("Event coming: $viewEvent")
        when (viewEvent) {
            TaskEvent.AddTaskClick -> openCreateTask()
            is TaskEvent.EditTaskClick -> openEditTask(viewEvent.taskId)
            is TaskEvent.DeleteTaskClick -> deleteTaskById(viewEvent.taskId)
            TaskEvent.SortingChanged -> sortingChanged()
        }
    }

    private fun sortingChanged() {
        viewState = viewState.copy(isTitleSorting = !viewState.isTitleSorting)
        _isTitleSorting.tryEmit(Unit)
    }

    private fun openCreateTask() {
        viewAction = TaskAction.OpenCreateTask
    }

    fun clearAction() {
        viewAction = null
    }

    private fun openEditTask(taskId: Long) {
        viewAction = TaskAction.OpenEditTask(taskId = taskId)
    }

    private fun deleteTaskById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            viewState = viewState.copy(isSending = true)
            viewState = try {
                taskRepository.deleteTask(id)
                viewState.copy(isSending = false)
            } catch (e: Exception) {
                viewState.copy(isSending = false)
            }
        }
    }
}