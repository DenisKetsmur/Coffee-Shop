package com.example.coffeeshop.screens.administrator.storage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.screens.cardForScreens.ChipGroup
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.ScrollViewModel
import com.example.coffeeshop.data.filled.productCategories
import com.example.coffeeshop.data.roomDone.products.room.entities.Product
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductViewModel
import com.example.coffeeshop.data.roomDone.products.room.entities.productsList
import com.example.coffeeshop.screens.administrator.components.CardStorageProduct
import com.example.coffeeshop.screens.cardForScreens.CatPop
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@Composable
fun StorageScreen(viewModel: ProductViewModel = viewModel()){
    val productList by viewModel.items.collectAsState()

    val router = LocalRouter.current
    StorageContent(
        products = productList,
        onClick = { product ->
            router.launch(AppRoute.Administrator.Storage.InformationProduct(product.id.toString()))
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StorageContent(
    products: List<Product>,
    onClick: (Product)-> Unit = {},
) {
    val viewModel: ScrollViewModel = viewModel()
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = viewModel.scrollState.value)

    LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemIndex } }) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { viewModel.scrollState.value = it }
    }

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    LazyColumn(
        state = listState
    ){
        stickyHeader {
            CustomOutlinedSearchTextField()
        }
        item{
            CatPop(
                modifier = Modifier.padding(start = 4.dp),
            ) { onIconStateChange ->
                ChipGroup(
                    categories = productCategories,
                    selectedCategories = selectedCategories,
                    onCategorySelected = { category ->
                        selectedCategories = category
                    },
                    onIconStateChange = { newState -> onIconStateChange(newState) },
                    modifier = Modifier.padding(start = 24.dp),
                    firstItemThreshold = 330f,
                    lastItemMaxThreshold = -150f,
                    endPadding = 350.dp,
                )
            }
        }
        items(products) { product->
            CardStorageProduct(
                product = product,
                onClick = { onClick(product) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewStorageScreen(){
    CoffeeAppTheme {
        Surface {
            StorageContent(productsList)
        }
    }
}