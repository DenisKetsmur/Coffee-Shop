package com.example.coffeeshop.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.user.ManagerUser
import com.example.navigationmodule.NavigationState
import com.example.navigationmodule.Router

val routesWithBackButton = setOf(
    AppRoute.StartUI.Login,
    AppRoute.Administrator.Purchase.InformationPurchase,
    AppRoute.Administrator.Purchase.AddSupplier,
    AppRoute.Administrator.Purchase.ShoppingCart,
    AppRoute.Administrator.Purchase.PurchaseInSupplier,
    AppRoute.Administrator.Purchase.EditSupplier,
    AppRoute.Administrator.Storage.EditProduct,
    AppRoute.Administrator.Storage.InformationProduct,
    AppRoute.Manager.Personal.EditPersonal,
    AppRoute.Manager.Personal.InfoPersonal,
    AppRoute.Manager.Clients.AddNewClientScreen,
    AppRoute.Manager.Clients.InfoClient
)

var routesWithoutActionButton = listOf(
    AppRoute.StartUI.Login,
    AppRoute.Administrator.Purchase.InformationPurchase,
    AppRoute.Administrator.Purchase.AddSupplier,
    AppRoute.Administrator.Purchase.ShoppingCart,
    AppRoute.Administrator.Purchase.EditSupplier,
    AppRoute.Administrator.Storage.EditProduct,
    AppRoute.Administrator.Storage.InformationProduct,
    AppRoute.Manager.Personal.EditPersonal,
    AppRoute.Manager.Personal.InfoPersonal,
    AppRoute.Manager.Clients.AddNewClientScreen,
    AppRoute.Manager.Clients.InfoClient
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    navigationState: NavigationState,
    router: Router,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource((navigationState.currentRoute as? AppRoute)?.titleRes?:R.string.logo))
        },
        navigationIcon = {
            if(navigationState.currentRoute in routesWithBackButton) {
                IconButton(
                    onClick = {
                        if(navigationState.currentRoute == AppRoute.Administrator.Purchase.InformationPurchase||
                            navigationState.currentRoute == AppRoute.Administrator.Purchase.PurchaseInSupplier ){
                            router.restart(AppRoute.Administrator.Purchase.RevisionPurchase)
                        }else router.pop()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }else{
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(R.string.logo),
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        },
        actions = {
            if(navigationState.currentRoute in routesWithoutActionButton){}
            else if(navigationState.currentRoute != AppRoute.StartUI.Login && ManagerUser.currentUser == null
                ){
                IconButton(onClick = { router.launch(AppRoute.StartUI.Login) }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(R.string.login),
                    )
                }
            }else if(
                navigationState.currentRoute == AppRoute.Administrator.Purchase.PurchaseInSupplier ||
                navigationState.currentRoute == AppRoute.StartUI.Menu){
                IconButton(onClick = {
                    router.launch(AppRoute.Administrator.Purchase.ShoppingCart) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                    )
                }
            } else if(navigationState.currentRoute != AppRoute.StartUI.Login &&
                ManagerUser.currentUser != null ){
                IconButton(onClick = {
                    ManagerUser.logout()
                    router.launch(AppRoute.StartUI.GeneralPageScreen) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(R.string.my_account),
                    )
                }
            }
        },
    )
}