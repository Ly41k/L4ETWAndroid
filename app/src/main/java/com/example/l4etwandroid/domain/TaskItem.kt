package com.example.l4etwandroid.domain

import java.util.Date

data class TaskItem(
    val id: Long,
    val title: String,
    val description: String,
    val date: Date
)
