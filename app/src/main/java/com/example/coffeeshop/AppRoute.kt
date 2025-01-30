package com.example.coffeeshop

import androidx.annotation.StringRes
import com.example.navigationmodule.Route


sealed class AppRoute(
    @StringRes val titleRes: Int
) : Route {

    sealed class Manager(
        @StringRes titleRes: Int
    ) : AppRoute(titleRes) {
        sealed class Personal(
            @StringRes titleRes: Int
        ): Manager(titleRes){
            object RevisionPersonal: Personal(R.string.personal)
            object EditPersonal: Personal(R.string.personal)
        }
        object Clients : Manager(R.string.clients)
    }

    sealed class Administrator(
        @StringRes titleRes: Int
    ) : AppRoute(titleRes) {
        object Storage : Administrator(R.string.storage)
        object Purchase : Administrator(R.string.purchase)
    }

    sealed class StartUI(
        @StringRes titleRes: Int
    ) : AppRoute(titleRes) {
        object Login : StartUI(R.string.login)
        object Menu : StartUI(R.string.coffee_shop)
        object GeneralPageScreen : StartUI(R.string.app_name)
    }
}