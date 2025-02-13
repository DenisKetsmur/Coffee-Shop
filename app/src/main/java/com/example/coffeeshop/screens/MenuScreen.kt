package com.example.coffeeshop.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.screens.cardForScreens.CatPop
import com.example.coffeeshop.screens.cardForScreens.ChipGroup
import com.example.coffeeshop.screens.administrator.components.CustomCardProduct
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.screens.cardForScreens.ThreeStateButton


@Composable
fun MenuScreen() {
    MenuContent()
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuContent() {
    var searchText by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки")
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    LazyColumn(
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
                        categories = categories,
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
        items(6) {
            CustomCardProduct()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMenuScreen() {
    MenuScreen()
}