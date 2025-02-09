package com.example.coffeeshop

import android.net.wifi.ScanResult.InformationElement
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.navigationmodule.Route


sealed class AppRoute(
    @StringRes val titleRes: Int,
    val icon: Int? = null
) : Route {

    sealed class Manager(
        @StringRes titleRes: Int,
        icon: Int? = null
    ) : AppRoute(titleRes, icon) {

        sealed class Personal(
            @StringRes titleRes: Int,
            icon: Int? = null,
        ) : Manager(titleRes, icon) {

            object RevisionPersonal : Personal(R.string.personal, icon = R.drawable.person_apron)
            object EditPersonal : Personal(R.string.personal)
        }

        sealed class Clients(
            @StringRes titleRes: Int,
            icon: Int? = null,
        ) : Manager(titleRes, icon) {
            object RevisionClients : Clients(R.string.clients, icon = R.drawable.person)
            object RevisionHistoryClient : Clients(R.string.clients)
            object AddNewClientScreen: Clients(R.string.add_new_client)
        }
    }

    sealed class Administrator(
        @StringRes titleRes: Int,
        icon: Int? = null,
    ) : AppRoute(titleRes, icon) {

        sealed class Storage(
            @StringRes titleRes: Int,
            icon: Int? = null,
        ) : Administrator(titleRes, icon) {
            object RevisionStorage : Storage(R.string.storage, icon = R.drawable.garage_home)
            object EditProduct : Storage(R.string.edit_product_screen)
            object InformationProduct: Storage(R.string.information_about_product_screen)
        }

        sealed class Purchase(
            @StringRes titleRes: Int,
            icon: Int? = null,
        ) : Administrator(titleRes, icon) {
            object RevisionPurchase : Purchase(R.string.purchase, icon = R.drawable.shopping_cart)
            object InformationPurchase : Purchase(R.string.purchase)
            object EditSupplier: Purchase(R.string.purchase)
        }
    }

    sealed class StartUI(
        @StringRes titleRes: Int,
        icon: Int? = null,
    ) : AppRoute(titleRes, icon) {
        object Login : StartUI(R.string.login)
        object Menu : StartUI(R.string.coffee_shop, icon = R.drawable.menu)
        object GeneralPageScreen : StartUI(R.string.app_name)
    }
}
