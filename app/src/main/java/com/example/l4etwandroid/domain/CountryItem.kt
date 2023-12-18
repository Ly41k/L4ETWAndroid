package com.example.l4etwandroid.domain

import com.example.l4etwandroid.core.utils.Constants.BASE_URL

data class CountryItem(
    val id: Int,
    val name: String,
    val icon: String
) {
    fun getIconUrl(): String = "$BASE_URL$icon"
}
