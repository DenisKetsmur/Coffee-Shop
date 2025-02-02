package com.example.coffeeshop.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.ManagerUser
import com.example.navigationmodule.NavigationState
import com.example.navigationmodule.Router

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
                if(navigationState.currentRoute == AppRoute.StartUI.Login ||
                    navigationState.currentRoute == AppRoute.Administrator.Purchase.InformationPurchase ||
                    navigationState.currentRoute == AppRoute.Administrator.Storage.EditStorage ||
                    navigationState.currentRoute == AppRoute.Manager.Personal.EditPersonal ||
                    navigationState.currentRoute == AppRoute.Manager.Clients.RevisionHistoryClient
                    ) {
                    IconButton(
                        onClick = {
                            router.pop()
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
                if(navigationState.currentRoute != AppRoute.StartUI.Login &&
                    ManagerUser.currentUser == null
                    ){
                    IconButton(onClick = { router.launch(AppRoute.StartUI.Login) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                        )
                    }
                }else if(navigationState.currentRoute != AppRoute.StartUI.Login &&
                    ManagerUser.currentUser != null ){
                    IconButton(onClick = {
                        ManagerUser.logout()
                        router.launch(AppRoute.StartUI.GeneralPageScreen) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                        )
                    }
                }
            },
        )
}