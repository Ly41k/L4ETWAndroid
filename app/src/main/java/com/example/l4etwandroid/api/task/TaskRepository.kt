package com.example.l4etwandroid.api.task

import com.example.l4etwandroid.domain.TaskItem
import java.time.LocalDate

interface TaskRepository {
    suspend fun getTasks()
    suspend fun addTask(title: String, description: String, date: LocalDate)
    suspend fun updateTask(item: TaskItem)
    suspend fun deleteTask(taskId: Long)
}