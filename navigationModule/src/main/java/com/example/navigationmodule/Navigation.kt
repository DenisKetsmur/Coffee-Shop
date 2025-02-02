package com.example.navigationmodule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.navigationmodule.internal.ScreenStack

data class Navigation(
    val router: Router,
    val navigationState: NavigationState,
)

@Composable
fun rememberNavigation(initialRoute: Route): Navigation {
    return remember(initialRoute) {
        val screenStack = ScreenStack(mutableStateListOf(initialRoute))
        Navigation(
            router = screenStack,
            navigationState = screenStack

        )
    }
}