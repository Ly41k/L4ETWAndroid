package com.example.l4etwandroid.compose

import androidx.compose.runtime.Composable
import com.example.l4etwandroid.core.navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun SplashScreen() {
    val rootController = LocalRootController.current
    rootController.push(NavigationTree.Auth.LoginScreen.name)
}