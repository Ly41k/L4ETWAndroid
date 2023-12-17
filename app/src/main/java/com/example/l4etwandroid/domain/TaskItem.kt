package com.example.l4etwandroid.domain

import com.example.l4etwandroid.api.task.models.TaskRequest
import com.example.l4etwandroid.core.utils.formatWithPattern
import java.time.LocalDate

data class TaskItem(
    val id: Long,
    val title: String,
    val description: String,
    val date: LocalDate
)

fun TaskItem.toTaskRequest(): TaskRequest {
    return TaskRequest(
        id = id,
        title = title,
        description = description,
        date = date.formatWithPattern()
    )
}
