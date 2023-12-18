package com.example.l4etwandroid.api.profile

import com.example.l4etwandroid.domain.ProfileItem

interface ProfileRepository {
    suspend fun getProfile()
    suspend fun updateTask(item: ProfileItem)
}