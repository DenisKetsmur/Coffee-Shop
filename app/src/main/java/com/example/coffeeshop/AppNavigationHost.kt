package com.example.coffeeshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coffeeshop.screens.EditPersonnel
import com.example.coffeeshop.screens.GeneralPageScreen
import com.example.coffeeshop.screens.LoginScreen
import com.example.coffeeshop.screens.MenuScreen
import com.example.coffeeshop.screens.PersonnelScreen
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
            AppRoute.Manager.Personal.RevisionPersonal -> EditPersonnelScreen()
        }
    }
}