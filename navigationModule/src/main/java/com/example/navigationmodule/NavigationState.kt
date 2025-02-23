package com.example.navigationmodule

import androidx.compose.runtime.Stable

@Stable
interface NavigationState {
    val isRoot:Boolean
    val currentRoute: Route
}