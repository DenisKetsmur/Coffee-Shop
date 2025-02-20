package com.example.coffeeshop.screens.shareScreens.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.filled.goodsCategories
import com.example.coffeeshop.data.filled.unitList
import com.example.coffeeshop.data.goods.entities.Goods
import com.example.coffeeshop.data.goods.entities.GoodsViewModel
import com.example.coffeeshop.data.goods.entities.goodsList
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import com.example.navigationmodule.LocalRouter
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EditGoodsScreen(
    goodsId: String,
    viewModel: GoodsViewModel = viewModel()
) {
    val goodsList by viewModel.items.collectAsState()
    val goods = goodsList.find { it.id == goodsId.toInt() }

    if(goods == null){
        Text(text = "goods null")
        return
    }

    EditProductContent(
        goods = goods,
        inUpdateGoods = {
            viewModel.edit(it)
        }
    )
}

@Composable
fun EditProductContent(
    goods: Goods,
    inUpdateGoods:(Goods) -> Unit = {},
) {
    var name by remember { mutableStateOf(goods.name) }
    var category by remember { mutableStateOf(goods.category) }
    var unit by remember { mutableStateOf(goods.unit) }
    var quantity by remember { mutableStateOf(goods.quantity.toString()) }
    var description by remember { mutableStateOf(goods.description) }
    
    val router = LocalRouter.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                CustomOutlinedInputTextField(
                    value = name,
                    onValueChange = { newValueName ->
                        name = newValueName
                    },
                    singleLine = true,
                    label = { Text(text = "Назва") },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomExposedDropdownMenuBox(
                    firstValue = goods.category,
                    options = goodsCategories,
                    onOptionsUpdated = { newValueCategory ->
                        category = newValueCategory
                    },
                    label = {
                        Text(
                            "Категорія"
                        )
                    }
                )
                CustomExposedDropdownMenuBox(
                    firstValue = goods.unit,
                    options = unitList,
                    onOptionsUpdated = { newValueUnit ->
                        unit = newValueUnit
                    },
                    label = {
                        Text(
                            "Одиниця виміру"
                        )
                    },
                    isSearchable = false
                )
                CustomOutlinedInputTextField(
                    value = quantity,
                    onValueChange = { newValueQuantity ->
                        quantity = newValueQuantity
                    },
                    singleLine = true,
                    label = {Text(text = "Кількість")},
                    modifier = Modifier.fillMaxWidth(),
                )
                CustomOutlinedInputTextField(
                    value = description,
                    onValueChange = { newValueDescription ->
                        description = newValueDescription
                    },
                    label = {Text(text = "Опис")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.padding(12.dp),
                        onClick = {
                            val updateProduct = goods.copy(
                                name = name,
                                category = category,
                                unit = unit,
                                quantity = quantity.toFloatOrNull() ?: 0f,
                                description = description
                            )
                            inUpdateGoods(updateProduct)
                            router.pop()
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                    ) {
                        Text(
                            text = "Підтвердити зміни"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewEditProductScreen(){
    EditProductContent(
        goods= goodsList[1],
    )
}