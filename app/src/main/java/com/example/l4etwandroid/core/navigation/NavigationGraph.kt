package com.example.l4etwandroid.core.navigation

import com.example.l4etwandroid.compose.auth.login.LoginScreen
import com.example.l4etwandroid.compose.main.tasks.TasksScreen
import com.example.l4etwandroid.compose.splash.SplashScreen
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.navigationGraph() {
    screen(NavigationTree.Splash.SplashScreen.name) { SplashScreen() }
    screen(NavigationTree.Auth.LoginScreen.name) { LoginScreen() }
    screen(NavigationTree.Main.TasksScreen.name) { TasksScreen() }
}