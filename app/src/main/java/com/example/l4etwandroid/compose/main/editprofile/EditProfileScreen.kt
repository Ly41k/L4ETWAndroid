package com.example.l4etwandroid.compose.main.editprofile

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.example.l4etwandroid.presentation.main.editprofile.EditProfileViewModel
import com.example.l4etwandroid.presentation.main.editprofile.models.EditProfileAction
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun EditProfileScreen() {

    val rootController = LocalRootController.current

    StoredViewModel(factory = { EditProfileViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState().value
        val action = viewModel.viewActions().observeAsState().value

        EditProfileView(state = state) { viewModel.obtainEvent(it) }

        when (action) {
            EditProfileAction.Back -> rootController.popBackStack()
            null -> {}
        }
    }
}