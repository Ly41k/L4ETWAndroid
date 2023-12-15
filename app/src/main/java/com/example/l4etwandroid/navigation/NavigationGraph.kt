package com.example.l4etwandroid.navigation

import com.example.l4etwandroid.compose.LoginScreen
import com.example.l4etwandroid.compose.SplashScreen
import com.example.l4etwandroid.core.navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.navigationGraph() {
    screen(NavigationTree.Splash.SplashScreen.name) { SplashScreen() }
    screen(NavigationTree.Auth.LoginScreen.name) { LoginScreen() }
}