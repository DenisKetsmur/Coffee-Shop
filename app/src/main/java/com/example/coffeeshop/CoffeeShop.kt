package com.example.coffeeshop

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.scaffold.AppFloatingActionButton
import com.example.coffeeshop.scaffold.AppNavigationBar
import com.example.coffeeshop.scaffold.AppToolBar
import com.example.navigationmodule.rememberNavigation

@Composable
fun CoffeeShop(
    darkTheme:Boolean,
    onThemeUpdate:()->Unit,
){
    val navigation = rememberNavigation(AppRoute.StartUI.GeneralPageScreen)
    val router = navigation.router
    val navigationState = navigation.navigationState

    Scaffold(
        topBar = {
            if(navigationState.currentRoute != AppRoute.StartUI.GeneralPageScreen){
                AppToolBar(
                    navigationState = navigationState,
                    router = router,
                    darkTheme = darkTheme,
                    onThemeUpdate = onThemeUpdate,
                )
            }
        },
        floatingActionButton = {
            if(navigationState.currentRoute in listOf(
                    AppRoute.Menu.Menu,
                    AppRoute.Manager.Personal.RevisionPersonal,
                    AppRoute.Administrator.Purchase.RevisionPurchase) &&
                ManagerUser.currentUser != null
            ) {
                AppFloatingActionButton(
                    navigationState = navigationState,
                    router = router,
                )
            }
        },
        bottomBar = {
            if(navigationState.currentRoute != AppRoute.Administrator.Purchase.ShoppingCart &&
                navigationState.currentRoute != AppRoute.StartUI.GeneralPageScreen
                ){
                AppNavigationBar(
                    navigationState = navigationState,
                    router = router,
                )
            }
        },
    ) { paddingValues ->
        AppNavigationHost(
            navigation = navigation,
            modifier = Modifier.padding(paddingValues)
        )
    }
}