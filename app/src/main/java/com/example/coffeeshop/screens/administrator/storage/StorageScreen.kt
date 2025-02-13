package com.example.coffeeshop.screens.administrator.storage

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.rawMaterial
import com.example.coffeeshop.data.filled.rawMaterialCategories
import com.example.coffeeshop.screens.administrator.components.CardStorageProduct
import com.example.coffeeshop.screens.cardForScreens.CatPop
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.navigationmodule.LocalRouter


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StorageScreen() {
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    val router = LocalRouter.current
    LazyColumn{
        stickyHeader {
            CustomOutlinedSearchTextField()
        }
        item{
            CatPop(
                modifier = Modifier.padding(start = 4.dp),
            ) { onIconStateChange ->
                ChipGroup(
                    categories = rawMaterialCategories,
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
        items(rawMaterial) {
            CardStorageProduct(
                rawMaterial = it,
                onRoute = {router.launch(AppRoute.Administrator.Storage.InformationProduct)}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewStorageScreen(){
    StorageScreen()
}