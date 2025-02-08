package com.example.coffeeshop.screens.Administrator
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.screens.CardForScreens.CustomExposedDropdownMenuBox

@Composable
fun EditProductScreen() {
    EditProductContent(
        Product(
            name = "Какао",
            category = "Молоко",
            description = "влаоптвол апвл опж пжовиапжолви пваоп жвлоап вапв" +
                    "в длаптвєдал птвдєлатп євлдатпєдлв тап єваптєвлдатплдєв атп" +
                    "в лдптєдлатпє втапдєлвт аєплдт ваєплвтаєплд тваєдпл твап ",
            price = "24234",
            unit = "мл",
            quantity = 234.0,
        ),
        onProductUpdated = {}
    )
}


@Composable
fun EditProductContent(
    product:Product,
    onProductUpdated: (Product) -> Unit
) {
    var name by remember { mutableStateOf(product.name) }
    var category by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(product.quantity.toString()) }
    var description by remember { mutableStateOf(product.description!!) }

    val categoryList = listOf("Apple", "Banana", "Cherry", "Date", "Fig", "Grapes")
    val unitList = listOf("кг", "мл", "л", "г")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            //.wrapContentHeight(),
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
                OutlinedTextField(
                    value = name,
                    onValueChange = { newValueName ->
                        name = newValueName
                    },
                    singleLine = true,
                    label = {Text(text = "Назва")},
                    modifier = Modifier.fillMaxWidth(),
                )
                CustomExposedDropdownMenuBox(
                    options = categoryList,
                    onOptionsUpdated = { newValueCategory ->
                        category = newValueCategory
                    },
                    label = {
                        Text(
                            "Виберіть категорію"
                        )
                    }
                )
                CustomExposedDropdownMenuBox(
                    options = unitList,
                    onOptionsUpdated = { newValueUnit ->
                        unit = newValueUnit
                    },
                    label = {
                        Text(
                            "Виберіть одиницю виміру"
                        )
                    }
                )
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { newValueQuantity ->
                        quantity = newValueQuantity
                    },
                    singleLine = true,
                    label = {Text(text = "Кількість")},
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
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
                    FloatingActionButton(
                        modifier = Modifier.padding(12.dp),
                        onClick = {
                            val updateProduct = product.copy(
                                name = name,
                                category = category,
                                unit = unit,
                                quantity = quantity.toDoubleOrNull() ?: 0.0,
                                description = description
                            )
                            onProductUpdated(updateProduct)
                        },
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        //modifier = Modifier.border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(15.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null
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
        Product(
            name = "Какао",
            category = "Молоко",
            description = "влаоптвол апвл опж пжовиапжолви пваоп жвлоап вапв" +
                    "в длаптвєдал птвдєлатп євлдатпєдлв тап єваптєвлдатплдєв атп" +
                    "в лдптєдлатпє втапдєлвт аєплдт ваєплвтаєплд тваєдпл твап ",
            price = "24234",
            unit = "мл",
            quantity = 234.0,
        ),
        onProductUpdated = {}
    )
}