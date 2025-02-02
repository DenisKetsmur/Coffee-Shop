package com.example.coffeeshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coffeeshop.screens.Administrator.InformationPurchaseScreen
import com.example.coffeeshop.screens.Administrator.PurchaseScreen
import com.example.coffeeshop.screens.Administrator.StorageScreen
import com.example.coffeeshop.screens.Manager.EditPersonnelScreen
import com.example.coffeeshop.screens.GeneralPageScreen
import com.example.coffeeshop.screens.LoginScreen
import com.example.coffeeshop.screens.Manager.ClientsScreen
import com.example.coffeeshop.screens.MenuScreen
import com.example.coffeeshop.screens.Manager.PersonnelScreen
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
            AppRoute.Manager.Personal.RevisionPersonal -> PersonnelScreen()
            AppRoute.Manager.Personal.EditPersonal -> EditPersonnelScreen()
            AppRoute.Manager.Clients.RevisionClients -> ClientsScreen()
            AppRoute.Manager.Clients.RevisionHistoryClient -> TODO()
            AppRoute.Administrator.Purchase.RevisionPurchase -> PurchaseScreen()
            AppRoute.Administrator.Purchase.InformationPurchase -> InformationPurchaseScreen()
            AppRoute.Administrator.Storage.RevisionStorage -> StorageScreen()
            AppRoute.Administrator.Storage.EditStorage-> TODO()
        }
    }
}