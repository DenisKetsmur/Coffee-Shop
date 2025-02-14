package com.example.coffeeshop

import ThemePreferences
import ThemeViewModel
import ThemeViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coffeeshop.ui.theme.CoffeeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = ThemePreferences(this)
        val viewModel: ThemeViewModel by viewModels { ThemeViewModelFactory(preferences) }

        enableEdgeToEdge()
        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()
            CoffeeAppTheme(darkTheme = isDarkTheme) {
                CoffeeShop(viewModel)
            }
        }
    }
}

