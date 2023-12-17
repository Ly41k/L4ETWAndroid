package com.example.l4etwandroid.presentation.main.addoredittask

import com.adeo.kviewmodel.BaseSharedViewModel
import com.example.l4etwandroid.api.task.TaskRepository
import com.example.l4etwandroid.core.di.Inject
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.core.utils.toDateString
import com.example.l4etwandroid.core.utils.toLocalDateOrToday
import com.example.l4etwandroid.domain.TaskItem
import com.example.l4etwandroid.presentation.main.addoredittask.models.AddOrEditAction
import com.example.l4etwandroid.presentation.main.addoredittask.models.AddOrEditEvent
import com.example.l4etwandroid.presentation.main.addoredittask.models.AddOrEditViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class AddOrEditViewModel(
    taskId: Long?
) : BaseSharedViewModel<AddOrEditViewState, AddOrEditAction, AddOrEditEvent>(
    initialState = AddOrEditViewState(id = taskId)
) {

    private val taskRepository: TaskRepository = Inject.instance()
    private val taskStore: ClearableBaseStore<List<TaskItem>> = Inject.instance()

    init {
        flow { emit(viewState.id) }
            .filterNotNull()
            .flatMapLatest { id -> taskStore.observe().mapNotNull { it.firstOrNull { it.id == id } } }
            .onEach {
                viewState = viewState.copy(
                    title = it.title,
                    description = it.description,
                    date = it.date
                )
            }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    override fun obtainEvent(viewEvent: AddOrEditEvent) {
        println("Event coming: $viewEvent")
        when (viewEvent) {
            is AddOrEditEvent.DateChanged -> dateChanged(viewEvent.value)
            AddOrEditEvent.DatePickedShow -> {
                viewState = viewState.copy(isDatePickerShow = !viewState.isDatePickerShow)
            }

            AddOrEditEvent.AddTaskClick -> addNewTask()
            AddOrEditEvent.EditTaskClick -> editTask()
            AddOrEditEvent.NavigateBack -> navigateBack()
            is AddOrEditEvent.DescriptionChanged -> obtainDescriptionChanged(viewEvent.value)
            is AddOrEditEvent.TitleChanged -> obtainTitleChanged(viewEvent.value)
        }
    }

    private fun dateChanged(value: Long) {
        viewState = viewState.copy(date = value.toDateString().toLocalDateOrToday())
    }

    private fun obtainTitleChanged(value: String) {
        viewState = viewState.copy(title = value)
    }

    private fun obtainDescriptionChanged(value: String) {
        viewState = viewState.copy(description = value)
    }

    private fun navigateBack() {
        viewAction = AddOrEditAction.Back
    }

    private fun addNewTask() {
        viewModelScope.launch(Dispatchers.IO) {
            viewState = viewState.copy(isSending = true)
            try {
                taskRepository.addTask(
                    title = viewState.title,
                    description = viewState.description,
                    date = viewState.date ?: LocalDate.now()
                )
                viewState = viewState.copy(isSending = false)
                viewAction = AddOrEditAction.Back
            } catch (e: Exception) {
                viewState = viewState.copy(isSending = false)
            }
        }
    }

    private fun editTask() {
        viewModelScope.launch(Dispatchers.IO) {
            viewState = viewState.copy(isSending = true)
            try {
                taskRepository.updateTask(
                    TaskItem(
                        id = viewState.id!!,
                        title = viewState.title,
                        description = viewState.description,
                        date = viewState.date ?: LocalDate.now()
                    )
                )
                viewState = viewState.copy(isSending = false)
                viewAction = AddOrEditAction.Back
            } catch (e: Exception) {
                viewState = viewState.copy(isSending = false)
            }
        }
    }
}