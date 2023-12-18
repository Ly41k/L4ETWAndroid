package com.example.l4etwandroid.api.profile

import com.example.l4etwandroid.domain.ProfileItem

interface ProfileRepository {
    suspend fun getProfile()
    suspend fun updateProfile(item: ProfileItem)
    fun logout()
}