package com.example.coffeeshop.screens.manager.client

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.CoffeeShop
import com.example.coffeeshop.data.ScrollViewModel
import com.example.coffeeshop.data.user.ClientViewModel
import com.example.coffeeshop.data.user.EmployeeViewModel
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.manager.components.CardClients
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@SuppressLint("ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClientsScreen(viewModel: ClientViewModel = viewModel()) {
    val router = LocalRouter.current

    val clientList by viewModel.items.collectAsState()

    val viewModel: ScrollViewModel = viewModel()
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = viewModel.scrollState.value)

    LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemIndex } }) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { viewModel.scrollState.value = it }
    }

    LazyColumn(
        state = listState
    ) {
        stickyHeader {
            CustomOutlinedSearchTextField()
        }
        items(clientList){ client ->
            CardClients(
                clientId = client.id,
                modifier = Modifier.clickable {
                    router.launch(AppRoute.Manager.Clients.InfoClient(clientId = client.id.toString()))
                },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewClientsScreen() {
    CoffeeAppTheme {
        Surface {
            ClientsScreen()
        }
    }
}