package com.example.l4etwandroid.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CommonTextField(
    text: String,
    hint: String,
    enabled: Boolean = true,
    isSecure: Boolean = false,
    trailingIcon: @Composable () -> Unit = {},
    onValueChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        value = text,
        visualTransformation = if (isSecure) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        placeholder = { Text(text = hint, color = MaterialTheme.colorScheme.tertiary) },
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            trailingIcon.invoke()
        },
        enabled = enabled,
        onValueChange = { onValueChanged(it) }
    )
}