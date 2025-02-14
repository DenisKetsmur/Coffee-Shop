package com.example.coffeeshop

import ThemeViewModel
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
    viewModel: ThemeViewModel
){
    val navigation = rememberNavigation(AppRoute.StartUI.GeneralPageScreen)
    val (router, navigationState) = navigation
    Scaffold(
        topBar = {
            if(navigationState.currentRoute != AppRoute.StartUI.GeneralPageScreen){
                AppToolBar(
                    navigationState = navigationState,
                    router = router,
                    viewModel,
                )
            }
        },
        floatingActionButton = {
            if(navigationState.currentRoute == AppRoute.Menu.Menu ||
                navigationState.currentRoute == AppRoute.Manager.Personal.RevisionPersonal ||
                navigationState.currentRoute == AppRoute.Administrator.Purchase.RevisionPurchase
                ){
                AppFloatingActionButton(
                    router = router,
                    navigationState = navigationState,
                )
            }
        },
        bottomBar = {
            if(ManagerUser.currentUser != null &&
                navigationState.currentRoute != AppRoute.Administrator.Purchase.ShoppingCart){
                AppNavigationBar(
                    navigationState,
                    router
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