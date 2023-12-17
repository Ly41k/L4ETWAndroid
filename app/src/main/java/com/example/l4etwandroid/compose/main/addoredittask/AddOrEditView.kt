package com.example.l4etwandroid.compose.main.addoredittask

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l4etwandroid.core.utils.DATE_PATTERN_YYYY_MM_DD
import com.example.l4etwandroid.core.utils.formatWithPattern
import com.example.l4etwandroid.presentation.main.addoredittask.models.AddOrEditEvent
import com.example.l4etwandroid.presentation.main.addoredittask.models.AddOrEditViewState
import com.example.l4etwandroid.ui.widget.CommonTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrEditView(state: AddOrEditViewState, eventHandler: (AddOrEditEvent) -> Unit) {

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = state.getDateInMillis())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = if (state.isCreateTask()) "Create Task" else "Edit Task",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        CommonTextField(
            text = state.title,
            hint = "Title",
            enabled = !state.isSending,
            onValueChanged = { eventHandler(AddOrEditEvent.TitleChanged(it)) })

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        CommonTextField(
            text = state.description,
            hint = "Description",
            enabled = !state.isSending,
            onValueChanged = { eventHandler(AddOrEditEvent.DescriptionChanged(it)) })

        Spacer(modifier = Modifier.padding(bottom = 16.dp))


        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable { eventHandler(AddOrEditEvent.DatePickedShow) },
            value = state.getDateOrToday().formatWithPattern(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            enabled = false,
            placeholder = { Text(text = DATE_PATTERN_YYYY_MM_DD, color = MaterialTheme.colorScheme.tertiary) },
            shape = RoundedCornerShape(10.dp),
            readOnly = true,
            onValueChange = {}
        )

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

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
                onClick = { eventHandler(AddOrEditEvent.NavigateBack) }) {
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
                    if (state.isCreateTask()) eventHandler(AddOrEditEvent.AddTaskClick)
                    else eventHandler(AddOrEditEvent.EditTaskClick)
                }) {
                Text(
                    text = if (state.isCreateTask()) "Create" else "Edit",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    if (state.isDatePickerShow) {
        DatePickerDialog(
            onDismissRequest = { eventHandler(AddOrEditEvent.DatePickedShow) },
            confirmButton = {
                TextButton(onClick = {
                    eventHandler(AddOrEditEvent.DatePickedShow)
                    datePickerState.selectedDateMillis?.let { eventHandler(AddOrEditEvent.DateChanged(it)) }
                }) { Text(text = "Confirm") }
            },
            dismissButton = {
                TextButton(onClick = { eventHandler(AddOrEditEvent.DatePickedShow) }) { Text(text = "Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}