package com.example.coffeeshop.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.user.ManagerUser
import com.example.navigationmodule.NavigationState
import com.example.navigationmodule.Router

val routesWithBackButton = setOf(
    AppRoute.StartUI.Login,
    AppRoute.StartUI.Registration,
    AppRoute.Menu.InfoProduct(null),
    AppRoute.Menu.EditProduct(null),
    AppRoute.Administrator.Purchase.InformationPurchase,
    AppRoute.Administrator.Purchase.AddSupplier,
    AppRoute.Administrator.Purchase.ShoppingCart,
    AppRoute.Administrator.Purchase.PurchaseInSupplier,
    AppRoute.Administrator.Purchase.EditSupplier,
    AppRoute.Administrator.Storage.EditProduct(null),
    AppRoute.Administrator.Storage.InformationProduct(null),
    AppRoute.Manager.Personal.EditPersonal(null),
    AppRoute.Manager.Personal.InfoPersonal(null),
    AppRoute.Manager.Clients.InfoClient(null),
    AppRoute.Manager.Clients.EditClient(null),
)

@Composable
fun AppToolBar(
    navigationState: NavigationState,
    router: Router,
    darkTheme: Boolean,
    onThemeUpdate: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                if (navigationState.currentRoute in routesWithBackButton) {
                    IconButton(
                        onClick = {
                            if (navigationState.currentRoute == AppRoute.Administrator.Purchase.InformationPurchase ||
                                navigationState.currentRoute == AppRoute.Administrator.Purchase.PurchaseInSupplier
                            ) router.restart(AppRoute.Administrator.Purchase.RevisionPurchase)
                            else router.pop()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                } else {
                    IconButton(
                        onClick = { onThemeUpdate.invoke() },
                    ) {
                        Icon(
                            painter = if (darkTheme) painterResource(id = R.drawable.dark_mode)
                            else painterResource(id = R.drawable.light_1),
                            contentDescription = stringResource(R.string.toggle_theme),
                            tint = Color.Unspecified,
                        )
                    }
                }
            }

            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(
                        (navigationState.currentRoute as? AppRoute)?.titleRes ?: R.string.logo
                    ),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                if (navigationState.currentRoute == AppRoute.StartUI.MyProfile(null) &&
                    ManagerUser.currentUser != null
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                ManagerUser.logout()
                                router.launch(AppRoute.StartUI.GeneralPageScreen)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Вийти",
                            modifier = Modifier.padding(end = 8.dp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(R.string.my_account),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }else if(navigationState.currentRoute == AppRoute.Administrator.Purchase.PurchaseInSupplier){
                    IconButton(
                        onClick = { router.launch(AppRoute.Administrator.Purchase.ShoppingCart) },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.shopping_cart),
                            contentDescription = stringResource(R.string.shopping_cart),
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}