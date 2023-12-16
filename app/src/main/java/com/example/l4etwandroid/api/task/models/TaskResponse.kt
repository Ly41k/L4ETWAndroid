package com.example.l4etwandroid.api.task.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    @SerialName("id") val id: Long?,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("date") val date: String,
)
