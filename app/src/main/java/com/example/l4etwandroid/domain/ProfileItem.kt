package com.example.l4etwandroid.domain

import com.example.l4etwandroid.api.profile.models.ProfileRequest

data class ProfileItem(
    val firstName: String = "",
    val lastName: String = "",
    val countryId: Int? = null
)

fun ProfileItem.toProfileRequest(): ProfileRequest {
    return ProfileRequest(
        firstName = this.firstName,
        lastName = this.lastName,
        countryId = this.countryId
    )
}
