package com.example.l4etwandroid.api.task

import com.example.l4etwandroid.domain.TaskItem

interface TaskRepository {
    suspend fun getTasks()
    suspend fun addTask(item: TaskItem)
    suspend fun updateTask(item: TaskItem)
    suspend fun deleteTask(taskId: Long)
}