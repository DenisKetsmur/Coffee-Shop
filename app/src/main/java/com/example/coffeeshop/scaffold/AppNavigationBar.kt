package com.example.coffeeshop.scaffold

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.data.user.ManagerUser.users
import com.example.navigationmodule.NavigationState
import com.example.navigationmodule.Router

val ManagerTabs = listOf(
    AppRoute.Menu.Menu,
    AppRoute.Manager.Personal.RevisionPersonal,
    AppRoute.Manager.Clients.RevisionClients,
    AppRoute.StartUI.MyProfile(userId = ManagerUser.currentUserId.toString())
)

val AdminTabs = listOf(
    AppRoute.Menu.Menu,
    AppRoute.Administrator.Storage.RevisionStorage,
    AppRoute.Administrator.Purchase.RevisionPurchase,
    AppRoute.StartUI.MyProfile(userId = ManagerUser.currentUserId.toString())
)

val SupplierTab = listOf(
    AppRoute.Administrator.Purchase.InformationPurchase,
    AppRoute.Administrator.Purchase.PurchaseInSupplier,
)

val ClientTabs = listOf(
    AppRoute.Menu.Menu,
    AppRoute.Client.ShoppingCart,
    AppRoute.StartUI.MyProfile(userId = ManagerUser.currentUserId.toString())
)


@Composable
fun AppNavigationBar(
    navigationState: NavigationState,
    router: Router,
) {
    NavigationBar {
        val currentTabs = when{
            navigationState.currentRoute == AppRoute.Administrator.Purchase.InformationPurchase -> SupplierTab
            navigationState.currentRoute == AppRoute.Administrator.Purchase.PurchaseInSupplier -> SupplierTab
            ManagerUser.isAdmin() -> AdminTabs
            ManagerUser.isManager() -> ManagerTabs
            else-> ClientTabs
         }

        currentTabs.forEach { tab ->
            NavigationBarItem(
                selected = navigationState.currentRoute == tab,
                label = {
                    Text(text = stringResource(tab.titleRes), fontSize = 10.sp, modifier = Modifier.weight(1f)) },
                onClick = { router.restart(tab) },
                icon = {
                    Icon(
                        imageVector =  ImageVector.vectorResource(tab.icon!!),
                        contentDescription = stringResource(tab.titleRes),
                        modifier = Modifier.size(24.dp),
                    )
                },
            )
        }
    }
}