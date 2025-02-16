package com.example.coffeeshop

import EditClientScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coffeeshop.screens.shareScreens.GeneralPageScreen
import com.example.coffeeshop.screens.shareScreens.LoginScreen
import com.example.coffeeshop.screens.shareScreens.menu.MenuScreen
import com.example.coffeeshop.screens.shareScreens.MyProfileScreen
import com.example.coffeeshop.screens.administrator.purchase.AddSupplierScreen
import com.example.coffeeshop.screens.administrator.purchase.EditSupplierScreen
import com.example.coffeeshop.screens.administrator.purchase.InformationPurchaseScreen
import com.example.coffeeshop.screens.administrator.purchase.PurchaseInSupplierScreen
import com.example.coffeeshop.screens.administrator.purchase.PurchaseScreen
import com.example.coffeeshop.screens.shareScreens.ShoppingCartScreen
import com.example.coffeeshop.screens.administrator.storage.EditRawProductScreen
import com.example.coffeeshop.screens.administrator.storage.InformationRawProductScreen
import com.example.coffeeshop.screens.administrator.storage.StorageScreen
import com.example.coffeeshop.screens.shareScreens.menu.AddProductScreen
import com.example.coffeeshop.screens.shareScreens.menu.EditProductScreen
import com.example.coffeeshop.screens.shareScreens.menu.InformationProductScreen
import com.example.coffeeshop.screens.manager.employee.AddNewClientScreen
import com.example.coffeeshop.screens.manager.client.ClientsScreen
import com.example.coffeeshop.screens.manager.client.InformationClientScreen
import com.example.coffeeshop.screens.manager.employee.AddNewEmployeeScreen
import com.example.coffeeshop.screens.manager.employee.EditEmployeeScreen
import com.example.coffeeshop.screens.manager.employee.EmployeeScreen
import com.example.coffeeshop.screens.manager.employee.InfoEmployeeScreen
import com.example.navigationmodule.Navigation
import com.example.navigationmodule.NavigationHost


@Composable
fun AppNavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
) {
    NavigationHost(
        navigation = navigation,
        modifier = modifier
    ) { currentRoute ->
        when (currentRoute) {
            AppRoute.StartUI.Login -> LoginScreen()
            AppRoute.StartUI.GeneralPageScreen -> GeneralPageScreen()
            AppRoute.StartUI.MyProfile -> MyProfileScreen()

            AppRoute.Menu.Menu -> MenuScreen()
            AppRoute.Menu.InfoProduct -> InformationProductScreen()
            AppRoute.Menu.EditProduct -> EditProductScreen()
            AppRoute.Menu.AddProduct -> AddProductScreen()

            AppRoute.Client.ShoppingCart -> ShoppingCartScreen()

            AppRoute.Manager.Personal.RevisionPersonal -> EmployeeScreen()
            AppRoute.Manager.Personal.InfoPersonal -> InfoEmployeeScreen()
            AppRoute.Manager.Personal.EditPersonal -> EditEmployeeScreen()
            AppRoute.Manager.Personal.AddNewPersonal -> AddNewEmployeeScreen()

            AppRoute.Manager.Clients.RevisionClients -> ClientsScreen()
            AppRoute.Manager.Clients.AddNewClientScreen -> AddNewClientScreen()
            AppRoute.Manager.Clients.InfoClient -> InformationClientScreen()
            AppRoute.Manager.Clients.EditClient -> EditClientScreen()

            AppRoute.Administrator.Purchase.RevisionPurchase -> PurchaseScreen()
            AppRoute.Administrator.Purchase.InformationPurchase -> InformationPurchaseScreen()
            AppRoute.Administrator.Purchase.AddSupplier -> AddSupplierScreen()
            AppRoute.Administrator.Purchase.PurchaseInSupplier -> PurchaseInSupplierScreen()
            AppRoute.Administrator.Purchase.ShoppingCart -> ShoppingCartScreen()
            AppRoute.Administrator.Purchase.EditSupplier -> EditSupplierScreen()

            AppRoute.Administrator.Storage.RevisionStorage -> StorageScreen()
            AppRoute.Administrator.Storage.EditProduct-> EditRawProductScreen()
            AppRoute.Administrator.Storage.InformationProduct -> InformationRawProductScreen()
        }
    }
}