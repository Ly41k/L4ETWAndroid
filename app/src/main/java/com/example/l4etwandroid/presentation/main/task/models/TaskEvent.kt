package com.example.l4etwandroid.presentation.main.task.models

sealed class TaskEvent {
    data object AddTaskClick : TaskEvent()
    data object SortingChanged : TaskEvent()
    data class EditTaskClick(val taskId: Long) : TaskEvent()
    data class DeleteTaskClick(val taskId: Long) : TaskEvent()

    data object EditProfileClick : TaskEvent()

    data object LogoutClick : TaskEvent()

    data object ScreenLoaded : TaskEvent()

}