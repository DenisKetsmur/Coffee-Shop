package com.example.coffeeshop

import androidx.annotation.StringRes
import com.example.navigationmodule.Route


sealed class AppRoute(
    @StringRes val  titleRes: Int = 0
): Route {
    sealed class Manager(
        @StringRes titleRes: Int = 0
    ):AppRoute(){
        object Personal:Manager(R.string.personal)
        object Clients:Manager(R.string.clients)
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