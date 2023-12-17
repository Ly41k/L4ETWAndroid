package com.example.l4etwandroid.compose.main.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l4etwandroid.domain.TaskItem
import com.example.l4etwandroid.presentation.main.task.models.TaskEvent
import com.example.l4etwandroid.presentation.main.task.models.TaskViewState

@Composable
fun TasksView(state: TaskViewState, eventHandler: (TaskEvent) -> Unit) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { if (!state.isSending) eventHandler(TaskEvent.AddTaskClick) },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add contact"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Text(
                    text = "List of Tasks",
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = state.isTitleSorting, onClick = {
                        eventHandler(TaskEvent.SortingChanged)
                    })
                    Text(text = "by Title")

                    RadioButton(selected = !state.isTitleSorting, onClick = {
                        eventHandler(TaskEvent.SortingChanged)
                    })
                    Text(text = "by Date")
                }
            }

            items(state.tasks) { item: TaskItem ->
                TaskItemView(
                    item = item,
                    onEditTaskClick = {
                        if (!state.isSending) eventHandler(TaskEvent.EditTaskClick(it))
                    },
                    onDeleteTaskClick = {
                        if (!state.isSending) eventHandler(TaskEvent.DeleteTaskClick(it))
                    }
                )
            }
        }
    }
}

