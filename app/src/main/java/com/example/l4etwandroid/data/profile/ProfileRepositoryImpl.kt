package com.example.l4etwandroid.data.profile

import com.example.l4etwandroid.api.profile.ProfileRepository
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.data.profile.ktor.KtorProfileRemoteDataSource
import com.example.l4etwandroid.domain.ProfileItem
import com.example.l4etwandroid.domain.toProfileRequest

class ProfileRepositoryImpl(
    private val remoteDataSource: KtorProfileRemoteDataSource,
    private val profileStore: ClearableBaseStore<ProfileItem>
) : ProfileRepository {
    override suspend fun getProfile() {
        val response = remoteDataSource.performGetProfile()
        val profile =
            ProfileItem(firstName = response.firstName, lastName = response.lastName, countryId = response.countryId)
        profileStore.publish(profile)
    }

    override suspend fun updateTask(item: ProfileItem) {
        remoteDataSource.performUpdateProfile(item.toProfileRequest())
        getProfile()
    }
}