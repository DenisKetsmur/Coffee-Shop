package com.example.coffeeshop.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.navigationmodule.NavigationState
import com.example.navigationmodule.Router


@Composable
fun AppFloatingActionButton(
    navigationState: NavigationState,
    modifier: Modifier = Modifier,
    router: Router
) {
    if(navigationState.currentRoute == AppRoute.Manager.Personal.RevisionPersonal){
        FloatingActionButton(
            modifier = modifier,
            onClick = { router.launch(AppRoute.Manager.Personal.AddNewPersonal) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.added_new_client)
            )
        }
    }else if(navigationState.currentRoute == AppRoute.Administrator.Purchase.RevisionPurchase){
        FloatingActionButton(
            modifier = modifier,
            onClick = { router.launch(AppRoute.Administrator.Purchase.AddSupplier) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.added_new_client)
            )
        }
    }else if(navigationState.currentRoute == AppRoute.Menu.Menu){
        FloatingActionButton(
            modifier = modifier,
            onClick = { router.launch(AppRoute.Menu.AddProduct) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.added_new_client)
            )
        }
    }
}
