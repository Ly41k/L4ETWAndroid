package com.example.l4etwandroid.data.profile

import com.example.l4etwandroid.api.profile.ProfileRepository
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.data.profile.ktor.KtorProfileRemoteDataSource
import com.example.l4etwandroid.data.profile.store.ProfileStore
import com.example.l4etwandroid.domain.ProfileItem
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val profileModule = DI.Module("profileModule") {
    bind<ProfileRepository>() with singleton {
        ProfileRepositoryImpl(instance(), instance(), instance())
    }

    bind<KtorProfileRemoteDataSource>() with provider {
        KtorProfileRemoteDataSource(instance(), instance())
    }

    bind<ClearableBaseStore<ProfileItem>>() with singleton { ProfileStore() }
}