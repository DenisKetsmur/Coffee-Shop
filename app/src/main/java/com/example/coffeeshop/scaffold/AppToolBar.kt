package com.example.coffeeshop.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import com.example.navigationmodule.NavigationState
import com.example.navigationmodule.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    navigationState: NavigationState,
    router: Router,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource((navigationState.currentRoute as? AppRoute)?.titleRes?:R.string.logo))  },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo),
                tint = Color.Unspecified,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        actions = {
            if(navigationState.currentRoute != AppRoute.StartUI.Login){
                IconButton(onClick = { router.launch(AppRoute.StartUI.Login) }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                    )
                }
            }
        },
    )
}