package com.example.l4etwandroid.data.country

import com.example.l4etwandroid.api.country.CountryRepository
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.data.country.ktor.KtorCountryRemoteDataSource
import com.example.l4etwandroid.domain.CountryItem

class CountryRepositoryImpl(
    private val remoteDataSource: KtorCountryRemoteDataSource,
    private val countryStore: ClearableBaseStore<List<CountryItem>>
) : CountryRepository {
    override suspend fun getCountries() {
        val response = remoteDataSource.performGetCountries()
        val items = response.map { country ->
            CountryItem(
                id = country.id,
                name = country.name,
                icon = country.icon,
            )
        }
        countryStore.publish(items)
    }
}
