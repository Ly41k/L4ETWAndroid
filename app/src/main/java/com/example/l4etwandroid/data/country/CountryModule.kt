package com.example.l4etwandroid.data.country

import com.example.l4etwandroid.api.country.CountryRepository
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.data.country.ktor.KtorCountryRemoteDataSource
import com.example.l4etwandroid.data.country.store.CountryStore
import com.example.l4etwandroid.domain.CountryItem
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val countryModule = DI.Module("countryModule") {
    bind<CountryRepository>() with singleton {
        CountryRepositoryImpl(instance(), instance())
    }

    bind<KtorCountryRemoteDataSource>() with provider {
        KtorCountryRemoteDataSource(instance(), instance())
    }

    bind<ClearableBaseStore<List<CountryItem>>>() with singleton { CountryStore() }
}