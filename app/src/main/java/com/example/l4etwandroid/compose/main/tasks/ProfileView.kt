package com.example.l4etwandroid.compose.main.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.l4etwandroid.presentation.main.task.models.TaskViewState

@Composable
fun ProfileView(
    state: TaskViewState,
    onEditProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = state.profile.iconUrl,
                contentDescription = "flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(70.dp)
                    .padding(start = 16.dp)
                    .background(Color.Gray)

            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
            ) {
                Text(text = "Hello")
                Text(text = state.profile.getFullName())

            }
            Box(
                modifier = Modifier.clickable {
                    onEditProfileClick.invoke()
                },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                )
            }
            Box(modifier = Modifier.clickable {
                onLogoutClick.invoke()
            }, contentAlignment = Alignment.Center) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",

                    )
            }

        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), color = Color.Gray
        )
    }
}