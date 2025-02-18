package com.example.coffeeshop.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ScrollViewModel : ViewModel() {
    var scrollState = mutableStateOf(0)
}

