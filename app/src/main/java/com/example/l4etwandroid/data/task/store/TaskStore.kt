package com.example.l4etwandroid.data.task.store

import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.domain.TaskItem

class TaskStore : ClearableBaseStore<List<TaskItem>>(
    getInitialValue = { emptyList() },
    getClearValue = { emptyList() }
)
