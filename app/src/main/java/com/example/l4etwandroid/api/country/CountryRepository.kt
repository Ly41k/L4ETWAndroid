package com.example.l4etwandroid.api.country

interface CountryRepository {
    suspend fun getCountries()
}