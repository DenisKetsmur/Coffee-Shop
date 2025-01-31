package com.example.coffeeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coffeeshop.screens.Administrator.InformationPurchaseScreen
import com.example.coffeeshop.screens.Administrator.PurchaseScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InformationPurchaseScreen()
        }
    }
}

