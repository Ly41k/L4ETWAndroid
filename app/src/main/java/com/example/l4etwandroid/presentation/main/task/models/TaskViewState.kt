package com.example.l4etwandroid.presentation.main.task.models

import com.example.l4etwandroid.domain.TaskItem

data class TaskViewState(
    val tasks: List<TaskItem>,
    val isSending: Boolean = false,
    val isTitleSorting: Boolean = false
)