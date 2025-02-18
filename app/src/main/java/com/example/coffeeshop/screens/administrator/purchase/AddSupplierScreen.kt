package com.example.coffeeshop.screens.administrator.purchase

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.navigationmodule.LocalRouter

@Composable
fun AddSupplierScreen() {
    AddSupplierContent()
}

@Composable
fun AddSupplierContent(
    //onSaveSuccess: () -> Unit = {}
) {
    val router = LocalRouter.current
    var products by remember { mutableStateOf(listOf(Product())) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.End
    ) {
        item {
            SupplierInfoInout()

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.TopCenter
                ){
                    Text(
                        text = "Товари",
                        fontSize = 32.sp,
                    )
                }
                products.forEachIndexed { index, rawMaterial ->
                    //if(index != 0) Divider(thickness = 4.dp)
                    Text(
                        text = "Товар ${index+1}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                    ProductInputItem1(
                        product = rawMaterial,
                        onProductChange = { updatedProduct ->
                            products = products.toMutableList().apply {
                                set(index, updatedProduct)
                            }
                        },

                        onRemove = {
                            products = products.toMutableList().apply {
                                if(products.size == 1){
                                    set(index, Product())
                                }else{
                                    removeAt(index)
                                }
                            }
                        }

                    )
                }
            }

            FloatingActionButton(
                onClick = {
                    products = products + Product()
                },
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = 12.dp),
                elevation = FloatingActionButtonDefaults.elevation(
                    pressedElevation = 4.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    //onSaveSuccess()
                    router.pop()
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
            .padding(top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Інформація про постачальника",
                fontSize = 20.sp,
            )
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

@Composable
fun ProductInputItem(
    product: Product,
    onProductChange: (Product) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            OutlinedTextField(
                value = product.name,
                onValueChange = { onProductChange(product.copy(name = it)) },
                label = { Text("Назва товару") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = product.category,
                onValueChange = { onProductChange(product.copy(category = it)) },
                label = { Text("Категорія") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedTextField(
                    value = product.price.toString(),
                    onValueChange = { onProductChange(product.copy(price = it.toDouble())) },
                    label = { Text("Ціна") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = product.unit,
                    onValueChange = { onProductChange(product.copy(unit = it)) },
                    label = { Text("Одиниця виміру") },
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedTextField(
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

@Preview(showSystemUi = true)
@Composable
private fun PreviewAddSupplierScreen(){
    AddSupplierScreen()
}