package com.example.coffeeshop.screens.administrator.purchase.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.filled.productCategories
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField

@Composable
fun ProductInfoEdit(
    product: Product,
    onProductChange: (Product) -> Unit,
    onRemove: () -> Unit
) {
    var price by remember { mutableStateOf(product.price.toString()) }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            CustomOutlinedInputTextField(
                value = product.name,
                onValueChange = { onProductChange(product.copy(name = it)) },
                label = { Text("Назва товару") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            CustomExposedDropdownMenuBox(
                firstValue = product.categoryId,
                options = productCategories,
                onOptionsUpdated = { newValueCategory ->
                    onProductChange(product.copy(categoryId = newValueCategory))
                },
                label = {
                    Text(
                        "Категорія"
                    )
                },
                isSearchable = true,
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                CustomOutlinedInputTextField(
                    value = price,
                    onValueChange = { newValuePrice ->
                        price = newValuePrice
                        onProductChange(product.copy(price = price.toDoubleOrNull() ?: 0.0))
                    },
                    singleLine = true,
                    label = {Text(text = "Кількість")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                )

                Spacer(modifier = Modifier.width(8.dp))
                CustomOutlinedInputTextField(
                    value = product.unitId,
                    onValueChange = { onProductChange(product.copy(unitId = it)) },
                    label = { Text("Одиниця виміру") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                )
            }

            CustomOutlinedInputTextField(
                value = product.description,
                onValueChange = { onProductChange(product.copy(description = it)) },
                label = { Text("Опис") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onRemove,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Видалити")
            }
        }
    }
}