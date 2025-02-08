package com.example.coffeeshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coffeeshop.screens.Administrator.EditProductScreen
import com.example.coffeeshop.screens.Administrator.InformationProductScreen
import com.example.coffeeshop.screens.Administrator.InformationPurchaseScreen
import com.example.coffeeshop.screens.Administrator.PurchaseScreen
import com.example.coffeeshop.screens.Administrator.StorageScreen
import com.example.coffeeshop.screens.GeneralPageScreen
import com.example.coffeeshop.screens.LoginScreen
import com.example.coffeeshop.screens.Manager.AddNewClientScreen
import com.example.coffeeshop.screens.Manager.ClientsScreen
import com.example.coffeeshop.screens.Manager.EditEmployeeScreen
import com.example.coffeeshop.screens.Manager.EmployeeScreen
import com.example.coffeeshop.screens.MenuScreen
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
            AppRoute.StartUI.Menu -> MenuScreen()
            AppRoute.StartUI.Login -> LoginScreen()
            AppRoute.StartUI.GeneralPageScreen -> GeneralPageScreen()

            AppRoute.Manager.Personal.RevisionPersonal -> EmployeeScreen()
            AppRoute.Manager.Personal.EditPersonal -> EditEmployeeScreen()
            AppRoute.Manager.Clients.RevisionClients -> ClientsScreen()
            AppRoute.Manager.Clients.RevisionHistoryClient -> TODO()
            AppRoute.Manager.Clients.AddNewClientScreen -> AddNewClientScreen()

            AppRoute.Administrator.Purchase.RevisionPurchase -> PurchaseScreen()
            AppRoute.Administrator.Purchase.InformationPurchase -> InformationPurchaseScreen()
            AppRoute.Administrator.Storage.RevisionStorage -> StorageScreen()
            AppRoute.Administrator.Storage.EditProduct-> EditProductScreen()
            AppRoute.Administrator.Storage.InformationProduct -> InformationProductScreen()
        }
    }
}