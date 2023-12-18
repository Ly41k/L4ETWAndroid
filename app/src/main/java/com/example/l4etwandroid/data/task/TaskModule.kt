package com.example.l4etwandroid.data.task

import com.example.l4etwandroid.api.task.TaskRepository
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.core.utils.LogoutHelper
import com.example.l4etwandroid.core.utils.LogoutHelperImpl
import com.example.l4etwandroid.data.task.ktor.KtorTaskRemoteDataSource
import com.example.l4etwandroid.data.task.store.TaskStore
import com.example.l4etwandroid.domain.TaskItem
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val taskModule = DI.Module("taskModule") {
    bind<TaskRepository>() with singleton {
        TaskRepositoryImpl(instance(), instance())
    }

    bind<KtorTaskRemoteDataSource>() with provider {
        KtorTaskRemoteDataSource(instance(), instance())
    }

    bind<ClearableBaseStore<List<TaskItem>>>() with singleton { TaskStore() }

    bind<LogoutHelper>() with singleton {
        LogoutHelperImpl(instance(), instance(), instance(), instance(), instance())
    }
}