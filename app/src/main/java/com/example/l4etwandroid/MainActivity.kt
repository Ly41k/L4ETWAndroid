package com.example.l4etwandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.l4etwandroid.core.di.Inject
import com.example.l4etwandroid.core.navigation.navigationGraph
import com.example.l4etwandroid.ui.theme.L4ETWAndroidTheme
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            L4ETWAndroidTheme {
                val configuration = OdysseyConfiguration(canvas = this)
                setNavigationContent(configuration) { navigationGraph() }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    L4ETWAndroidTheme {
//        Greeting("Android")
//    }
//}