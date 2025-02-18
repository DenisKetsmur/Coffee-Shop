package com.example.coffeeshop.screens.shareScreens.menu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.productCategories
import com.example.coffeeshop.data.productAndGoods.GoodsRepository
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.screens.cardForScreens.CatPop
import com.example.coffeeshop.screens.cardForScreens.ChipGroup
import com.example.coffeeshop.screens.cardForScreens.CustomCardGoods
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.screens.cardForScreens.ThreeStateButton
import com.example.navigationmodule.LocalRouter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.data.ScrollViewModel
import com.example.coffeeshop.data.productAndGoods.GoodsViewModel


@Composable
fun MenuScreen() {
    MenuContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuContent(viewModel: GoodsViewModel = viewModel()) {
    val goodsList by viewModel.goods.collectAsState()

    val viewModel: ScrollViewModel = viewModel()
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = viewModel.scrollState.value)

    LaunchedEffect(listState.firstVisibleItemIndex) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { viewModel.scrollState.value = it }
    }

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    val router = LocalRouter.current
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()

    ) {
        stickyHeader {
            CustomOutlinedSearchTextField()
        }
        item{
            Row(
                modifier = Modifier.fillMaxWidth(),
            ){
                ThreeStateButton(
                    modifier = Modifier.padding(start = 16.dp, top = 36.dp)
                )
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
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }
            }
        }
        items(goodsList) { goods ->
            CustomCardGoods(
                goodsId = goods.id,
                modifier = Modifier.clickable {
                    router.launch(AppRoute.Menu.InfoProduct(goods.id.toString()))
                },
                enableButtonAddInShoppingCart = ManagerUser.isClient()
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMenuScreen() {
    MenuScreen()
}