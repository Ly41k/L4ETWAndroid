package com.example.l4etwandroid.api.profile.models

import kotlinx.serialization.SerialName

data class ProfileResponse(
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("countryId") val countryId: Int?,
)
