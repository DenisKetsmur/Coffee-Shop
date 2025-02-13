package com.example.coffeeshop.screens.administrator.purchase

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.sampleSupplier
import com.example.coffeeshop.screens.administrator.components.CardSupplier
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.navigationmodule.LocalRouter


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PurchaseScreen() {
    val router = LocalRouter.current

    LazyColumn {
        stickyHeader {
            CustomOutlinedSearchTextField()
        }
        items(8){
            CardSupplier(sampleSupplier){router.launch(AppRoute.Administrator.Purchase.InformationPurchase)}
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewPurchaseScreen(){
    PurchaseScreen()
}