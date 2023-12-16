package com.example.l4etwandroid.compose.main.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.l4etwandroid.domain.TaskItem

@Composable
fun TaskItemView(
    item: TaskItem,
    onEditTaskClick: (Long) -> Unit,
    onDeleteTaskClick: (Long) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(text = item.title)
                Text(text = item.description)
                Text(text = item.date.toString())
            }
            Box(modifier = Modifier.clickable { onEditTaskClick(item.id) }) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                )
            }
            Box(modifier = Modifier.clickable { onDeleteTaskClick(item.id) }) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
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
