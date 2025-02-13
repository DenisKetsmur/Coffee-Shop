package com.example.coffeeshop.screens.manager.client

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffeeshop.CoffeeShop
import com.example.coffeeshop.data.filled.client
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.manager.components.CardClients
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme

@SuppressLint("ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClientsScreen() {
    LazyColumn {
        stickyHeader {
            CustomOutlinedSearchTextField()
        }
        items(6){
            CardClients(
                client = client
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