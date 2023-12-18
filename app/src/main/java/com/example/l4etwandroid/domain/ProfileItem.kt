package com.example.l4etwandroid.domain

import com.example.l4etwandroid.api.profile.models.ProfileRequest

data class ProfileItem(
    val firstName: String = "",
    val lastName: String = "",
    val countryId: Int? = null,
    val iconUrl: String? = null
) {
    fun getFullName(): String = "$firstName $lastName"
}

fun ProfileItem.toProfileRequest(): ProfileRequest {
    return ProfileRequest(
        firstName = this.firstName,
        lastName = this.lastName,
        countryId = this.countryId
    )
}
