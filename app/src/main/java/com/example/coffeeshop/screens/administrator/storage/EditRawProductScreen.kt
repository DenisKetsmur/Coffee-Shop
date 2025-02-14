package com.example.coffeeshop.screens.administrator.storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.filled.rawMaterialCategories
import com.example.coffeeshop.data.filled.unitList
import com.example.coffeeshop.data.product.RawMaterial
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.navigationmodule.LocalRouter

@Composable
fun EditRawProductScreen() {
    EditRawProductContent(
        RawMaterial(
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

@Composable
fun EditRawProductContent(
    rawMaterial: RawMaterial,
    onProductUpdated: (RawMaterial) -> Unit
) {
    var name by remember { mutableStateOf(rawMaterial.name) }
    var category by remember { mutableStateOf(rawMaterial.category) }
    var unit by remember { mutableStateOf(rawMaterial.unit) }
    var quantity by remember { mutableStateOf(rawMaterial.quantity.toString()) }
    var description by remember { mutableStateOf(rawMaterial.description) }


    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val router = LocalRouter.current

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
                    label = { Text(text = "Назва") },
                    modifier = Modifier.fillMaxWidth()
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                )
                CustomExposedDropdownMenuBox(
                    firstValue = rawMaterial.category,
                    options = rawMaterialCategories,
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
                    firstValue = rawMaterial.unit,
                    options = unitList,
                    onOptionsUpdated = { newValueUnit ->
                        unit = newValueUnit
                    },
                    label = {
                        Text(
                            "Виберіть одиницю виміру"
                        )
                    },
                    isSearchable = false
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
                    Button(
                        modifier = Modifier.padding(12.dp),
                        onClick = {
                            val updateProduct = rawMaterial.copy(
                                name = name,
                                category = category,
                                unit = unit,
                                quantity = quantity.toFloatOrNull() ?: 0f,
                                description = description
                            )
                            onProductUpdated(updateProduct)
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
private fun PreviewEditRawProductScreen(){
    EditRawProductContent(
        RawMaterial(
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