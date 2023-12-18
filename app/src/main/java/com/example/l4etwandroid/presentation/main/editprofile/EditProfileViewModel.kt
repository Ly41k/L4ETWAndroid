package com.example.l4etwandroid.presentation.main.editprofile

import com.adeo.kviewmodel.BaseSharedViewModel
import com.example.l4etwandroid.api.profile.ProfileRepository
import com.example.l4etwandroid.core.di.Inject
import com.example.l4etwandroid.core.store.ClearableBaseStore
import com.example.l4etwandroid.domain.CountryItem
import com.example.l4etwandroid.domain.ProfileItem
import com.example.l4etwandroid.presentation.main.editprofile.models.EditProfileAction
import com.example.l4etwandroid.presentation.main.editprofile.models.EditProfileEvent
import com.example.l4etwandroid.presentation.main.editprofile.models.EditProfileViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class EditProfileViewModel : BaseSharedViewModel<EditProfileViewState, EditProfileAction, EditProfileEvent>(
    initialState = EditProfileViewState()
) {

    private val profileRepository: ProfileRepository = Inject.instance()
    private val profileStore: ClearableBaseStore<ProfileItem> = Inject.instance()
    private val countryStore: ClearableBaseStore<List<CountryItem>> = Inject.instance()

    init {

        combine(profileStore.observe(), countryStore.observe()) { profile, countries ->
            viewState = viewState.copy(
                firstName = profile.firstName,
                lastName = profile.lastName,
                countryId = profile.countryId,
                countries = countries,
                selectedCountryName = countries.firstOrNull {
                    it.id == profile.countryId
                }?.name ?: "-"
            )
        }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }


    override fun obtainEvent(viewEvent: EditProfileEvent) {
        println("Event coming: $viewEvent")
        when (viewEvent) {
            EditProfileEvent.CountryPickerShow -> countryPickerShowed()
            EditProfileEvent.EditProfileClick -> updateProfile()
            is EditProfileEvent.FirstNameChanged -> obtainFirstNameChanged(viewEvent.value)
            is EditProfileEvent.LastNameChanged -> obtainLastNameChanged(viewEvent.value)
            EditProfileEvent.NavigateBack -> navigateBack()
            is EditProfileEvent.SelectedCountryChanged -> selectedCountryChanged(viewEvent.value)
        }
    }


    private fun countryPickerShowed() {
        viewState = viewState.copy(isExpandedCountryList = !viewState.isExpandedCountryList)
    }

    private fun selectedCountryChanged(item: CountryItem) {
        viewState = viewState.copy(
            countryId = item.id,
            selectedCountryName = item.name,
            isExpandedCountryList = false
        )
    }

    private fun updateProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            viewState = viewState.copy(isSending = true)
            try {
                profileRepository.updateProfile(
                    ProfileItem(
                        firstName = viewState.firstName,
                        lastName = viewState.lastName,
                        countryId = viewState.countryId,
                    )
                )
                viewState = viewState.copy(isSending = false)
                viewAction = EditProfileAction.Back
            } catch (e: Exception) {
                viewState = viewState.copy(isSending = false)
            }
        }
    }


    private fun obtainFirstNameChanged(value: String) {
        viewState = viewState.copy(firstName = value)
    }

    private fun obtainLastNameChanged(value: String) {
        viewState = viewState.copy(lastName = value)
    }

    private fun navigateBack() {
        viewAction = EditProfileAction.Back
    }
}