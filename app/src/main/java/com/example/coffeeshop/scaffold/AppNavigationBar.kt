package com.example.coffeeshop.scaffold

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.data.user.ManagerUser
import com.example.navigationmodule.NavigationState
import com.example.navigationmodule.Router

val ManagerTabs = listOf(
    AppRoute.StartUI.Menu,
    AppRoute.Manager.Personal.RevisionPersonal,
    AppRoute.Manager.Clients.RevisionClients,
)

val AdminTabs = listOf(
    AppRoute.StartUI.Menu,
    AppRoute.Administrator.Storage.RevisionStorage,
    AppRoute.Administrator.Purchase.RevisionPurchase,
)

val SupplierTab = listOf(
    AppRoute.Administrator.Purchase.InformationPurchase,
    AppRoute.Administrator.Purchase.PurchaseInSupplier,
)


@Composable
fun AppNavigationBar(
    navigationState: NavigationState,
    router: Router,
) {
    NavigationBar {
        val currentTabs = if(ManagerUser.isManager())
            ManagerTabs
        else if(navigationState.currentRoute == AppRoute.Administrator.Purchase.InformationPurchase ||
            navigationState.currentRoute == AppRoute.Administrator.Purchase.PurchaseInSupplier){
            SupplierTab
        }else
            AdminTabs

        currentTabs.forEach { tab ->
            NavigationBarItem(
                selected = navigationState.currentRoute == tab,
                label = { Text(stringResource(tab.titleRes)) },
                onClick = { router.restart(tab) },
                icon = {
                    Icon(
                        imageVector =  ImageVector.vectorResource(tab.icon!!),
                        contentDescription = stringResource(tab.titleRes),
                        modifier = Modifier.size(25.dp),
                    )
                },
            )
        }
    }
}