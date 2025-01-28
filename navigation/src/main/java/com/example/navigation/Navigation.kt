package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.navigation.internal.ScreenStack
import kotlin.math.round

data class Navigation(
    val router: Router,
    val navigationState: NavigationState,
)

@Composable
fun rememberNavigation(initialRoute: Route):Navigation{
    return remember(initialRoute) {
        val screenStack = ScreenStack(mutableStateListOf(initialRoute))
        Navigation(
            router = screenStack,
            navigationState = screenStack
        )
    }

}