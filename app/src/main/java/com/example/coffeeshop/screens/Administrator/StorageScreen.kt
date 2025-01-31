package com.example.coffeeshop.screens.Administrator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.BeeTablesCompose
import com.example.coffeeshop.screens.componentsMenuScreen.ChipGroup
import androidx.compose.foundation.layout.Box


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StorageScreen() {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки")
    var selectedCategory by remember { mutableStateOf<String?>(null) }


    LazyColumn {
        stickyHeader {
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                },
                modifier = Modifier
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
                )
            )
        }
        
        item {
            ChipGroup(
                categories,
                selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )
            val headerTitles = listOf("№", "Назва", "Категорія", "Од. вим.", "Кількість")
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp)
            ){
                BeeTablesCompose(
                    productList,
                    headerTableTitles = headerTitles,
                )
            }
        }
    }
}


data class Product(
    val id:Int? = null,
    val name:String,
    val category: String,
    val unit:String? = null,
    val count:Double? = null,
)

val productList = listOf(
    Product(
        id = 1,
        name = "Кава арабіка",
        category = "Сировина",
        unit = "кг",
        count = 10.0
    ),
    Product(
        id = 2,
        name = "Кава робуста",
        category = "Сировина",
        unit = "кг",
        count = 8.0
    ),
    Product(
        id = 3,
        name = "Молоко коров’яче",
        category = "Сировина",
        unit = "л",
        count = 25.0
    ),
    Product(
        id = 4,
        name = "Шоколад чорний",
        category = "Сировина",
        unit = "кг",
        count = 5.0
    ),
    Product(
        id = 5,
        name = "Цукор тростниковий",
        category = "Сировина",
        unit = "кг",
        count = 7.0
    ),
    Product(
        id = 6,
        name = "Мука пшенична",
        category = "Сировина",
        unit = "кг",
        count = 15.0
    ),
    Product(
        id = 7,
        name = "Яйця курячі",
        category = "Сировина",
        unit = "десятки",
        count = 3.0
    ),
    Product(
        id = 8,
        name = "Маргарин",
        category = "Сировина",
        unit = "кг",
        count = 4.0
    ),
    Product(
        id = 9,
        name = "Фрукти (яблука)",
        category = "Сировина",
        unit = "кг",
        count = 20.0
    ),
    Product(
        id = 10,
        name = "Дріжджі сухі",
        category = "Сировина",
        unit = "кг",
        count = 1.0
    ),
    Product(
        id = 1,
        name = "Кава арабіка",
        category = "Сировина",
        unit = "кг",
        count = 10.0
    ),
    Product(
        id = 2,
        name = "Кава робуста",
        category = "Сировина",
        unit = "кг",
        count = 8.0
    ),
    Product(
        id = 3,
        name = "Молоко коров’яче",
        category = "Сировина",
        unit = "л",
        count = 25.0
    ),
    Product(
        id = 4,
        name = "Шоколад чорний",
        category = "Сировина",
        unit = "кг",
        count = 5.0
    ),
    Product(
        id = 5,
        name = "Цукор тростниковий",
        category = "Сировина",
        unit = "кг",
        count = 7.0
    ),
    Product(
        id = 6,
        name = "Мука пшенична",
        category = "Сировина",
        unit = "кг",
        count = 15.0
    ),
    Product(
        id = 7,
        name = "Яйця курячі",
        category = "Сировина",
        unit = "десятки",
        count = 3.0
    ),
    Product(
        id = 8,
        name = "Маргарин",
        category = "Сировина",
        unit = "кг",
        count = 4.0
    ),
    Product(
        id = 9,
        name = "Фрукти (яблука)",
        category = "Сировина",
        unit = "кг",
        count = 20.0
    ),
    Product(
        id = 10,
        name = "Дріжджі сухі",
        category = "Сировина",
        unit = "кг",
        count = 1.0
    )
)


@Preview(showSystemUi = true)
@Composable
fun PreviewStorageScreen(){
    StorageScreen()
}