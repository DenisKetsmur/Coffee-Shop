package com.example.coffeeshop

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.navigation.navigation.Route
import com.example.coffeeshop.R


sealed class AppRoute(
    @StringRes val  titleRes: Int = 0
):Route{
    sealed class Meneger(
        @StringRes titleRes: Int = 0
    ):AppRoute(){
        object Personal:Meneger(R.string.personal)
        object Clients:Meneger(R.string.clients)
    }

    sealed class Administrator(
        @StringRes titleRes: Int = 0
    ):AppRoute(){
        object Storage:Administrator(R.string.storage)
        object Purchase:Administrator(R.string.purchase)
    }

    sealed class StartUI(
        @StringRes titleRes: Int = 0
    ):AppRoute(){
        object Login:Administrator(R.string.login)
        object Menu:Administrator(R.string.coffee_shop)
    }

}