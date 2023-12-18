package com.example.l4etwandroid.data.country.store

import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.domain.CountryItem

class CountryStore : ClearableBaseStore<List<CountryItem>>(
    getInitialValue = { emptyList() },
    getClearValue = { emptyList() }
)