package com.example.l4etwandroid.presentation.main.addoredittask.models

import java.time.LocalDate
import java.time.temporal.ChronoField

data class AddOrEditViewState(
    val id: Long?,
    val title: String = "",
    val description: String = "",
    val date: LocalDate? = null,
    val isDatePickerShow: Boolean = false,
    val isSending: Boolean = false,
) {

    fun getDateInMillis(): Long {
        return this.getDateOrToday().getLong(ChronoField.EPOCH_DAY) * 86400000
    }

    fun getDateOrToday(): LocalDate = date ?: LocalDate.now()

    fun isCreateTask(): Boolean = id == null
}
