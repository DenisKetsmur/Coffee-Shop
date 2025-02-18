package com.example.coffeeshop.screens.administrator.storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.data.filled.productCategories
import com.example.coffeeshop.data.filled.unitList
import com.example.coffeeshop.data.formatting.formatWithoutZero
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.data.productAndGoods.ProductViewModel
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@Composable
fun EditProductScreen(
    productId: String,
    viewModel: ProductViewModel = viewModel()
) {
    val productList by viewModel.items.collectAsState()
    val product = productList.find { it.id == productId.toInt() }

    val router = LocalRouter.current

    if(product == null){
        Text(text = "product null")
        return
    }

    EditProductContent(
        product = product,
        onProductUpdated = {
            viewModel.edit(it)
            router.pop()
        }
    )
}

@Composable
fun EditProductContent(
    product: Product,
    onProductUpdated: (Product) -> Unit
) {
    var name by remember { mutableStateOf(product.name) }
    var category by remember { mutableStateOf(product.category) }
    var unit by remember { mutableStateOf(product.unit) }
    var quantity by remember { mutableStateOf(product.quantity.toString()) }
    var description by remember { mutableStateOf(product.description) }

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
                    label = { Text(text = "Назва продукту") },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomExposedDropdownMenuBox(
                    firstValue = product.category,
                    options = productCategories,
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
                    firstValue = product.unit,
                    options = unitList,
                    onOptionsUpdated = { newValueUnit ->
                        unit = newValueUnit
                    },
                    label = {
                        Text(
                            "одиниця виміру"
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                        onClick = {
                            val updateProduct = product.copy(
                                name = name,
                                category = category,
                                unit = unit,
                                quantity = quantity.toFloatOrNull() ?: 0f,
                                description = description
                            )
                            onProductUpdated(updateProduct)
                        },
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
private fun PreviewEditRawProductScreen(){
    CoffeeAppTheme(darkTheme = true) {
        Surface {
            EditProductContent(
                Product(
                    name = "Какао",
                    category = "Молоко",
                    description = "влаоптвол апвл опж пжовиапжолви пваоп жвлоап вапв" +
                            "в длаптвєдал птвдєлатп євлдатпєдлв тап єваптєвлдатплдєв атп" +
                            "в лдптєдлатпє втапдєлвт аєплдт ваєплвтаєплд тваєдпл твап ",
                    unit = "мл",
                    quantity = 234f,
                ),
                onProductUpdated = {}
            )
        }
    }
}