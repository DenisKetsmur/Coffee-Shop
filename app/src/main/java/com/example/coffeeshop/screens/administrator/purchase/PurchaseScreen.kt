package com.example.coffeeshop.screens.administrator.purchase

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.ScrollViewModel
import com.example.coffeeshop.data.supplier.SupplierViewModel
import com.example.coffeeshop.data.user.ManagerSupplier
import com.example.coffeeshop.screens.administrator.purchase.card.CardSupplier
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PurchaseScreen(viewModel: SupplierViewModel = viewModel()) {
    val supplierList by viewModel.items.collectAsState()

    val viewModelScroll: ScrollViewModel = viewModel()
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = viewModelScroll.scrollState.value)

    LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemIndex } }) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { viewModelScroll.scrollState.value = it }
    }

    val router = LocalRouter.current

    LazyColumn(
        state = listState
    ) {
        stickyHeader {
            CustomOutlinedSearchTextField()
        }
        items(supplierList){ supplier ->
            CardSupplier(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .height(150.dp),
                supplierId = supplier.id.toString(),
                onClick = {
                    ManagerSupplier.launch(supplierId = supplier.id.toString())
                    router.launch(AppRoute.Administrator.Purchase.PurchaseInSupplier(supplier.id.toString()))
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewPurchaseScreen(){
    CoffeeAppTheme {
        Surface {
            PurchaseScreen()
        }
    }
}