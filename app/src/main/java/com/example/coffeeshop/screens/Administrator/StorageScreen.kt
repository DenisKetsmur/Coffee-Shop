package com.example.coffeeshop.screens.Administrator

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
import com.example.coffeeshop.screens.CardForScreens.ChipGroup
import androidx.compose.foundation.lazy.items
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.screens.CardForScreens.CardStorageProduct
import com.example.coffeeshop.screens.CardForScreens.CatPop
import com.example.coffeeshop.screens.CardForScreens.CustomOutlinedTextField


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StorageScreen() {
    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки", "Редагувати")
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    LazyColumn{
        stickyHeader {
            CustomOutlinedTextField()
        }
        item{
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
                    modifier = Modifier.padding(start = 24.dp),
                    firstItemThreshold = 330f,
                    lastItemMaxThreshold = -150f,
                    endPadding = 350.dp,
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