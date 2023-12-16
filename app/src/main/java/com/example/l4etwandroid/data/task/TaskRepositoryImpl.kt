package com.example.l4etwandroid.data.task

import com.example.l4etwandroid.api.task.TaskRepository
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.data.task.ktor.KtorTaskRemoteDataSource
import com.example.l4etwandroid.domain.TaskItem
import java.util.Date

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
                    date = Date()
                )
            }
        }
        taskStore.publish(items)
    }

    override suspend fun addTask(item: TaskItem) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(item: TaskItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: Long) {
        TODO("Not yet implemented")
    }
}