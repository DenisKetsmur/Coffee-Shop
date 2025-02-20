package com.example.coffeeshop.screens.administrator.storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.roomDone.products.room.entities.Product
import com.example.coffeeshop.data.roomDone.products.room.entities.ProductViewModel
import com.example.coffeeshop.data.roomDone.products.room.entities.productsList
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@Composable
fun InformationProductScreen(
    productId: String,
    viewModel: ProductViewModel = viewModel()
){
    val productList by viewModel.items.collectAsState()
    val product = productList.find { it.id == productId.toInt() }

    val router = LocalRouter.current

    if(product == null){
        Text(text = "product null")
        return
    }

    InformationProductContent(
        product = product,
        onClick = {
            router.launch(AppRoute.Administrator.Storage.EditProduct(productId))
        }
    )
}

@Composable
fun InformationProductContent(
    product: Product,
    onClick: ()-> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Назва: ${product.name}"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Категорія: ${product.categoryId}"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Одиниця виміру: ${product.unitId}"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Кількість: ${product.quantity}"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Опис: ${product.description}"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = {
                        onClick()
                    },
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "create "
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewInformationRawProductScreen(){
    CoffeeAppTheme(darkTheme = false) {
        InformationProductContent(
            product = productsList[1]
        )
    }
}