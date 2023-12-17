package com.example.l4etwandroid.presentation.main.addoredittask.models

sealed class AddOrEditEvent {
    data class DateChanged(val value: Long) : AddOrEditEvent()

    data class TitleChanged(val value: String) : AddOrEditEvent()
    data class DescriptionChanged(val value: String) : AddOrEditEvent()

    data object DatePickedShow : AddOrEditEvent()

    data object AddTaskClick : AddOrEditEvent()
    data object EditTaskClick : AddOrEditEvent()

    data object NavigateBack : AddOrEditEvent()

}