package com.example.l4etwandroid.presentation.main.editprofile.models

import com.example.l4etwandroid.domain.CountryItem

sealed class EditProfileEvent {
    data class FirstNameChanged(val value: String) : EditProfileEvent()
    data class LastNameChanged(val value: String) : EditProfileEvent()
    data object EditProfileClick : EditProfileEvent()
    data object NavigateBack : EditProfileEvent()
    data object CountryPickerShow : EditProfileEvent()
    data class SelectedCountryChanged(val value: CountryItem) : EditProfileEvent()
}