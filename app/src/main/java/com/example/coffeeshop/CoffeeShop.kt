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
fun CoffeeShop(){
    val navigation = rememberNavigation(AppRoute.StartUI.GeneralPageScreen)
    val (router, navigationState) = navigation
    Scaffold(
        topBar = {
            if(navigationState.currentRoute != AppRoute.StartUI.GeneralPageScreen){
                AppToolBar(
                    navigationState,
                    router
                )
            }
        },
        floatingActionButton = {
            if(navigationState.currentRoute == AppRoute.StartUI.Menu){
                AppFloatingActionButton(
                    onLaunchAction = {
                        TODO()
                    }
                )
            }
        },
        bottomBar = {
            if(ManagerUser.currentUser != null){
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