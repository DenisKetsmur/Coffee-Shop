package com.example.coffeeshop

import com.example.coffeeshop.data.ThemePreferences
import com.example.coffeeshop.data.ThemeViewModel
import com.example.coffeeshop.data.ThemeViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import com.example.coffeeshop.ui.theme.CoffeeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            CoffeeAppTheme(darkTheme = darkTheme) {
                CoffeeShop(
                    darkTheme = darkTheme,
                    onThemeUpdate = {darkTheme = !darkTheme}
                )
            }
        }
    }
}

