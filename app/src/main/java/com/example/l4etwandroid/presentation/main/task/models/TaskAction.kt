package com.example.l4etwandroid.presentation.main.task.models

sealed class TaskAction {
    data object OpenCreateTask : TaskAction()
    data class OpenEditTask(val taskId: Long) : TaskAction()

    data object OpenEditProfile : TaskAction()

    data object Logout : TaskAction()
}