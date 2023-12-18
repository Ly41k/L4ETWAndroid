package com.example.l4etwandroid.data.profile.store

import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.domain.ProfileItem

class ProfileStore : ClearableBaseStore<ProfileItem>(
    getInitialValue = { ProfileItem() },
    getClearValue = { ProfileItem() }
)
