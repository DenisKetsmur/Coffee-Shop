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
            object InfoPersonal: Personal(R.string.info)
            object EditPersonal : Personal(R.string.personal)
        }

        sealed class Clients(
            @StringRes titleRes: Int,
            icon: Int? = null,
        ) : Manager(titleRes, icon) {
            object RevisionClients : Clients(R.string.clients, icon = R.drawable.person)
            object AddNewClientScreen: Clients(R.string.add_new_client)
            object EditClient: Clients(R.string.edit)
            object InfoClient: Clients(R.string.info)
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
            object RevisionPurchase : Purchase(R.string.Suppliers, icon = R.drawable.groups)
            object InformationPurchase : Purchase(R.string.informatiom_about_supplier, icon = R.drawable.person_apron)
            object AddSupplier: Purchase(R.string.data_filling )
            object PurchaseInSupplier: Purchase(R.string.purchase, icon = R.drawable.shopping_cart)
            object ShoppingCart : Purchase(R.string.shopping_cart)
            object EditSupplier: Purchase(R.string.edit)
        }
    }

    sealed class StartUI(
        @StringRes titleRes: Int,
        icon: Int? = null,
    ) : AppRoute(titleRes, icon) {
        object Login : StartUI(R.string.login)
        object Menu : StartUI(R.string.goods, icon = R.drawable.menu)
        object GeneralPageScreen : StartUI(R.string.app_name)
    }
}
