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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.R
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.data.supplier.SupplierViewModel
import com.example.coffeeshop.data.supplier.suppliers
import com.example.coffeeshop.screens.administrator.purchase.card.ProductInfoEdit
import com.example.coffeeshop.screens.administrator.purchase.card.SupplierInfoEdit
import com.example.navigationmodule.LocalRouter

@Composable
fun EditSupplierScreen(
    supplierId: String,
    viewModel: SupplierViewModel = viewModel()
) {
    val supplierList = viewModel.items.collectAsState()
    val supplier = supplierList.value.find { it.id == supplierId.toInt() }

    val router = LocalRouter.current

    if(supplier == null){
        Text(text = "supplier null")
        return
    }
    EditSupplierContent(
        supplier = supplier,
        onSaveSuccess = {
            println("AAA: s${it}")
            viewModel.edit(it)
            router.pop()
        }
    )
}

@Composable
fun EditSupplierContent(
    supplier: Supplier,
    onSaveSuccess: (Supplier) -> Unit = {}
) {
    var updateSupplier by remember { mutableStateOf(supplier) }
    var products by remember { mutableStateOf(supplier.products) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.End
    ) {
        item {
            SupplierInfoEdit(
                supplier = updateSupplier,
                onSupplierUpdated = {
                    updateSupplier = updateSupplier.copy(
                        nameCompany = it.nameCompany,
                        phoneNumber = it.phoneNumber,
                        email = it.email
                    )
                    println("AAA: u${updateSupplier}")
                }
            )

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
                    Text(
                        text = "Товар ${index+1}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                    ProductInfoEdit(
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
                    contentDescription = stringResource(R.string.add_product_in_list)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    updateSupplier = updateSupplier.copy(
                        products = products
                    )
                    onSaveSuccess(updateSupplier)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Зберегти")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewEditSupplierScreen(){
    EditSupplierContent(
        supplier = suppliers[1]
    )
}