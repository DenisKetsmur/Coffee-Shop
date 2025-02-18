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
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            when (navigationState.currentRoute) {
                AppRoute.Manager.Personal.RevisionPersonal ->
                    router.launch(AppRoute.Manager.Personal.AddNewPersonal)
                AppRoute.Administrator.Purchase.RevisionPurchase ->
                    router.launch(AppRoute.Administrator.Purchase.AddSupplier)
                AppRoute.Menu.Menu ->
                    router.launch(AppRoute.Menu.AddProduct)
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.added_new_cart)
        )
    }
}
