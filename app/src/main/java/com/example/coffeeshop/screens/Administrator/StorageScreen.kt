package com.example.coffeeshop.screens.Administrator

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.screens.CardForScreens.ChipGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.graphics.graphicsLayer
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.screens.CardForScreens.CardStorageProduct
import com.example.coffeeshop.screens.CardForScreens.CatPop
import com.example.navigationmodule.LocalRouter


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StorageScreen() {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки", "Редагувати")
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    LazyColumn{
        stickyHeader {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { newText ->
                        searchText = newText
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    shape = RoundedCornerShape(28),
                    label = {
                        Text(
                            text = "Search",
                            color = Color.Gray
                        )
                    },
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                searchText = ""
                                focusManager.clearFocus()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Description",
                                tint = Color.Unspecified
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                )
        }
        item{
                CatPop(
                    modifier = Modifier.padding(start = 4.dp)
                ) { onIconStateChange ->
                    ChipGroup(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelected = { category ->
                            selectedCategory = category
                        },
                        onIconStateChange = { newState -> onIconStateChange(newState) },
                        modifier = Modifier.padding(start = 24.dp),
                        firstItemThreshold = 330f,
                        lastItemMaxThreshold = -150f,
                        endPadding = 350.dp
                    )
                }
        }
        items(productList) {
                CardStorageProduct(it)
        }
    }
}

val productList = listOf(
    Product(
        id = 1,
        name = "Кава",
        category = "Сировина",
        unit = "кг",
        quantity = 10.0
    ),
    Product(
        id = 2,
        name = "Кава робуста",
        category = "Сировина",
        unit = "кг",
        quantity = 8.0
    ),
    Product(
        id = 3,
        name = "Молоко коров’яче",
        category = "Сировина",
        unit = "л",
        quantity = 25.0
    ),
    Product(
        id = 4,
        name = "Шоколад чорний",
        category = "Сировина",
        unit = "кг",
        quantity = 5.0
    ),
    Product(
        id = 5,
        name = "Цукор тростниковий",
        category = "Сировина",
        unit = "кг",
        quantity = 7.0
    ),
    Product(
        id = 6,
        name = "Мука пшенична",
        category = "Сировина",
        unit = "кг",
        quantity = 15.0
    ),
    Product(
        id = 7,
        name = "Яйця курячі",
        category = "Сировина",
        unit = "десятки",
        quantity = 3.0
    ),
    Product(
        id = 8,
        name = "Маргарин",
        category = "Сировина",
        unit = "кг",
        quantity = 4.0
    ),
    Product(
        id = 9,
        name = "Фрукти (яблука)",
        category = "Сировина",
        unit = "кг",
        quantity = 20.0
    ),
    Product(
        id = 10,
        name = "Дріжджі сухі",
        category = "Сировина",
        unit = "кг",
        quantity = 1.0
    ),
    Product(
        id = 1,
        name = "Кава арабіка",
        category = "Сировина",
        unit = "кг",
        quantity = 10.0
    ),
    Product(
        id = 2,
        name = "Кава робуста",
        category = "Сировина",
        unit = "кг",
        quantity = 8.0
    ),
    Product(
        id = 3,
        name = "Молоко коров’яче",
        category = "Сировина",
        unit = "л",
        quantity = 25.0
    ),
    Product(
        id = 4,
        name = "Шоколад чорний",
        category = "Сировина",
        unit = "кг",
        quantity = 5.0
    ),
    Product(
        id = 5,
        name = "Цукор тростниковий",
        category = "Сировина",
        unit = "кг",
        quantity = 7.0
    ),
    Product(
        id = 6,
        name = "Мука пшенична",
        category = "Сировина",
        unit = "кг",
        quantity = 15.0
    ),
    Product(
        id = 7,
        name = "Яйця курячі",
        category = "Сировина",
        unit = "десятки",
        quantity = 3.0
    ),
    Product(
        id = 8,
        name = "Маргарин",
        category = "Сировина",
        unit = "кг",
        quantity = 4.0
    ),
    Product(
        id = 9,
        name = "Фрукти (яблука)",
        category = "Сировина",
        unit = "кг",
        quantity = 20.0
    ),
    Product(
        id = 10,
        name = "Дріжджі сухі",
        category = "Сировина",
        unit = "кг",
        quantity = 1.0
    )
)


@Preview(showSystemUi = true)
@Composable
fun PreviewStorageScreen(){
    StorageScreen()
}