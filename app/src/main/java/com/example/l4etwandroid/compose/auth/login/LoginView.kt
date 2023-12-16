package com.example.l4etwandroid.compose.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.l4etwandroid.presentation.auth.login.models.LoginEvent
import com.example.l4etwandroid.presentation.auth.login.models.LoginViewState
import com.example.l4etwandroid.ui.widget.CommonTextField

@Composable
fun LoginView(state: LoginViewState, eventHandler: (LoginEvent) -> Unit) {
    Column(modifier = Modifier.padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Login",
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(50.dp))

        CommonTextField(
            text = state.email,
            hint = "Your Login",
            enabled = !state.isSending
        ) { eventHandler.invoke(LoginEvent.EmailChanged(it)) }

        Spacer(modifier = Modifier.height(24.dp))

        CommonTextField(
            text = state.password,
            hint = "Your Password",
            enabled = !state.isSending,
            isSecure = state.passwordHidden,
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        eventHandler.invoke(LoginEvent.PasswordShowClick)
                    },
                    imageVector = if (state.passwordHidden) {
                        Icons.Outlined.Clear
                    } else {
                        Icons.Outlined.Lock
                    },
                    contentDescription = "Password hidden",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }) {
            eventHandler.invoke(LoginEvent.PasswordChanged(it))
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            enabled = !state.isSending,
            shape = RoundedCornerShape(10.dp),
            onClick = { eventHandler.invoke(LoginEvent.LoginClick) }) {
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}