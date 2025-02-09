package com.example.coffeeshop.screens.administrator.purchase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.product.RawMaterial

@Composable
fun EditSupplierScreen() {
    EditSupplierContent()
}

@Composable
fun EditSupplierContent(
    //onSaveSuccess: () -> Unit = {}
) {
    var rawMaterials by remember { mutableStateOf(listOf(RawMaterial())) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            SupplierInfoInout()

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Товари")

            rawMaterials.forEachIndexed { index, rawMaterial ->
                ProductInputItem(
                    rawMaterial = rawMaterial,
                    onProductChange = { updatedProduct ->
                        rawMaterials = rawMaterials.toMutableList().apply {
                            set(index, updatedProduct)
                        }
                    },

                    onRemove = {
                        rawMaterials = rawMaterials.toMutableList().apply {
                            removeAt(index)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(onClick = {
                rawMaterials = rawMaterials + RawMaterial()
            }) {
                Text("Додати товар")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    //onSaveSuccess()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Зберегти")
            }
        }
    }
}

@Composable
fun SupplierInfoInout(){
    var supplierName by remember { mutableStateOf("") }
    var supplierNumber by remember { mutableStateOf("") }
    var supplierEmail by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row{
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = supplierName,
                    onValueChange = { supplierName = it },
                    label = { Text("Назва компанії") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = supplierNumber,
                    onValueChange = { supplierNumber = it },
                    label = { Text("Номер телефону") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = supplierEmail,
                    onValueChange = { supplierEmail = it },
                    label = { Text("Пошта") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ProductInputItem(
    rawMaterial: RawMaterial,
    onProductChange: (RawMaterial) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = rawMaterial.name,
                onValueChange = { onProductChange(rawMaterial.copy(name = it)) },
                label = { Text("Назва товару") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = rawMaterial.category,
                onValueChange = { onProductChange(rawMaterial.copy(category = it)) },
                label = { Text("Категорія") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedTextField(
                    value = rawMaterial.price.toString(),
                    onValueChange = { onProductChange(rawMaterial.copy(price = it.toDouble())) },
                    label = { Text("Ціна") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = rawMaterial.unit,
                    onValueChange = { onProductChange(rawMaterial.copy(unit = it)) },
                    label = { Text("Одиниця виміру") },
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedTextField(
                value = rawMaterial.description,
                onValueChange = { onProductChange(rawMaterial.copy(description = it)) },
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

@Preview(showSystemUi = true)
@Composable
private fun PreviewEditSupplierScreen(){
    EditSupplierScreen()
}