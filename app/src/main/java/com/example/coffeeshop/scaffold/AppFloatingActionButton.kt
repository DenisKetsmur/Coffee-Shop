package com.example.coffeeshop.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
    if(navigationState.currentRoute == AppRoute.Manager.Clients.RevisionClients){
        FloatingActionButton(
            modifier = modifier,
            onClick = { router.launch(AppRoute.Manager.Clients.AddNewClientScreen) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.added_new_client)
            )
        }
    }else{
        FloatingActionButton(
            modifier = modifier,
            onClick = { TODO() }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = stringResource(R.string.turns_back_to_the_top)
            )
        }
    }
}
