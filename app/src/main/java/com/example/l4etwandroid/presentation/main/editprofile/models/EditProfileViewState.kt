package com.example.l4etwandroid.presentation.main.editprofile.models

import com.example.l4etwandroid.domain.CountryItem

data class EditProfileViewState(
    val firstName: String = "",
    val lastName: String = "",
    val countryId: Int? = null,
    val countries: List<CountryItem> = emptyList(),
    val selectedCountryName: String = "-",
    val isExpandedCountryList: Boolean = false,
    val isSending: Boolean = false,
)
