package com.example.coffeeshop

import androidx.annotation.StringRes
import com.example.coffeeshop.AppRoute.Manager.Clients
import com.example.coffeeshop.AppRoute.Manager.Clients.EditClient
import com.example.navigationmodule.Route

sealed class AppRoute(
    @StringRes val titleRes: Int,
    val icon: Int? = null,
) : Route {

    sealed class Manager(
        @StringRes titleRes: Int,
        icon: Int? = null
    ) : AppRoute(titleRes, icon) {

        sealed class Personal(
            @StringRes titleRes: Int,
            icon: Int? = null,
        ) : Manager(titleRes, icon) {
            data object RevisionPersonal : Personal(R.string.personal, icon = R.drawable.person_apron)
            data class InfoPersonal(val employeeId: String?): Personal(R.string.info){
                override fun equals(other: Any?): Boolean = other is InfoPersonal
                override fun hashCode(): Int = javaClass.hashCode()
            }
            data class EditPersonal(val employeeId: String?) : Personal(R.string.edit){
                override fun equals(other: Any?): Boolean = other is EditPersonal
                override fun hashCode(): Int = javaClass.hashCode()
            }
            data object AddNewPersonal : Personal(R.string.data_filling)
        }

        sealed class Clients(
                @StringRes titleRes: Int,
                icon: Int? = null,
            ) : Manager(titleRes, icon) {
            data object RevisionClients : Clients(R.string.clients, icon = R.drawable.person)
            data class EditClient(val clientId: String?): Clients(R.string.edit){
                    override fun equals(other: Any?): Boolean = other is EditClient
                    override fun hashCode(): Int = javaClass.hashCode()
                }
            data class InfoClient(val clientId: String?): Clients(R.string.info){
                    override fun equals(other: Any?): Boolean = other is InfoClient
                    override fun hashCode(): Int = javaClass.hashCode()
                }
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
            data object RevisionStorage : Storage(R.string.storage, icon = R.drawable.garage_home)
            data class EditProduct(val productId:String?) : Storage(R.string.edit_product_screen){
                override fun equals(other: Any?): Boolean = other is EditProduct
                override fun hashCode(): Int = javaClass.hashCode()
            }
            data class InformationProduct(val productId:String?): Storage(R.string.information_about_product_screen){
                override fun equals(other: Any?): Boolean = other is InformationProduct
                override fun hashCode(): Int = javaClass.hashCode()
            }
        }

        sealed class Purchase(
            @StringRes titleRes: Int,
            icon: Int? = null,
        ) : Administrator(titleRes, icon) {
            data object RevisionPurchase : Purchase(R.string.Suppliers, icon = R.drawable.groups)
            data object InformationPurchase : Purchase(R.string.informatiom_about_supplier, icon = R.drawable.person_apron)
            data object AddSupplier: Purchase(R.string.data_filling )
            data object PurchaseInSupplier: Purchase(R.string.purchase, icon = R.drawable.shopping_cart)
            data object ShoppingCart : Purchase(R.string.shopping_cart)
            data object EditSupplier: Purchase(R.string.edit)
        }

    }

    sealed class Client(
        @StringRes titleRes: Int,
        icon: Int? = null,
    ) : AppRoute(titleRes, icon){
        data object ShoppingCart : Client(R.string.shopping_cart, icon = R.drawable.shopping_cart)
    }

    sealed class Menu(
        @StringRes titleRes: Int,
        icon: Int? = null,
    ) : Administrator(titleRes, icon){

        data object MenuScreen : Menu(R.string.goods, icon = R.drawable.menu)
        data class InfoProduct(val productId: String?): Menu(R.string.info){
            override fun equals(other: Any?): Boolean = other is InfoProduct
            override fun hashCode(): Int = javaClass.hashCode()
        }
        data class EditProduct(val productId: String?): Menu(R.string.edit){
            override fun equals(other: Any?): Boolean = other is EditProduct
            override fun hashCode(): Int = javaClass.hashCode()
        }
        data object AddProduct : Menu(R.string.add_goods)
    }

    sealed class StartUI(
        @StringRes titleRes: Int,
        icon: Int? = null,
    ) : AppRoute(titleRes, icon) {
        data object Login : StartUI(R.string.login)
        data object Registration: StartUI(R.string.add_new_client)
        data object GeneralPageScreen : StartUI(R.string.app_name)
        data class MyProfile(val userId: String?): StartUI(R.string.my_profile, icon = R.drawable.account_circle)
    }
}
