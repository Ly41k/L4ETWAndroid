package com.example.l4etwandroid.data.task

import com.example.l4etwandroid.api.task.TaskRepository
import com.example.l4etwandroid.api.task.models.TaskRequest
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.core.utils.formatWithPattern
import com.example.l4etwandroid.core.utils.toLocalDateOrToday
import com.example.l4etwandroid.data.task.ktor.KtorTaskRemoteDataSource
import com.example.l4etwandroid.domain.TaskItem
import com.example.l4etwandroid.domain.toTaskRequest
import java.time.LocalDate

class TaskRepositoryImpl(
    private val remoteDataSource: KtorTaskRemoteDataSource,
    private val taskStore: ClearableBaseStore<List<TaskItem>>
) : TaskRepository {
    override suspend fun getTasks() {
        val response = remoteDataSource.performGetTasks()
        val items = response.mapNotNull { task ->
            task.id?.let { id ->
                TaskItem(
                    id = id,
                    title = task.title,
                    description = task.description,
                    date = task.date.toLocalDateOrToday()
                )
            }
        }
        taskStore.publish(items)
    }

    override suspend fun addTask(title: String, description: String, date: LocalDate) {
        remoteDataSource.performAddTask(
            TaskRequest(
                id = null, title = title, description = description, date = date.formatWithPattern()
            )
        )
        getTasks()
    }

    override suspend fun updateTask(item: TaskItem) {
        remoteDataSource.performEditTask(item.toTaskRequest())
        getTasks()
    }

    override suspend fun deleteTask(taskId: Long) {
        remoteDataSource.performDeleteTask(taskId)
        getTasks()
    }
}