package com.example.coffeeshop.screens.manager.client

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffeeshop.R
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.manager.components.CardClients
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedTextField

@SuppressLint("ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClientsScreen() {
    LazyColumn {
        stickyHeader {
            CustomOutlinedTextField()
        }
        items(6){
            CardClients(client = User.Client())
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewClientsScreen() {
    ClientsScreen()
}