package com.example.l4etwandroid.compose.main.editprofile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l4etwandroid.presentation.main.editprofile.models.EditProfileEvent
import com.example.l4etwandroid.presentation.main.editprofile.models.EditProfileViewState
import com.example.l4etwandroid.ui.widget.CommonTextField

@Composable
fun EditProfileView(state: EditProfileViewState, eventHandler: (EditProfileEvent) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = "Edit Profile",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )

        CommonTextField(
            text = state.firstName,
            hint = "First name",
            enabled = !state.isSending,
            onValueChanged = { eventHandler(EditProfileEvent.FirstNameChanged(it)) })

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            text = state.lastName,
            enabled = !state.isSending,
            hint = "Last name",
            onValueChanged = { eventHandler(EditProfileEvent.LastNameChanged(it)) })

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
            TextField(
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable(onClick = {
                        if (!state.isSending) eventHandler(EditProfileEvent.CountryPickerShow)
                    }),
                value = state.selectedCountryName,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = {}
            )

            DropdownMenu(
                expanded = state.isExpandedCountryList,
                onDismissRequest = { eventHandler(EditProfileEvent.CountryPickerShow) },
                modifier = Modifier.wrapContentWidth()
            ) {
                state.countries.forEach { country ->
                    DropdownMenuItem(
                        modifier = Modifier,
                        onClick = { eventHandler(EditProfileEvent.SelectedCountryChanged(country)) },
                        text = { Text(text = country.name) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(end = 2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = !state.isSending,
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    eventHandler(EditProfileEvent.NavigateBack)
                }) {
                Text(
                    text = "Back",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(start = 2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = !state.isSending,
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    eventHandler(EditProfileEvent.EditProfileClick)

                }) {
                Text(
                    text = "Edit",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

