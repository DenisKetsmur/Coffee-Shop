package com.example.coffeeshop.screens.administrator.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.data.formatting.formatWithoutZero
import com.example.coffeeshop.data.roomDone.products.room.entities.Product
import com.example.coffeeshop.data.roomDone.products.room.entities.productsList
import com.example.coffeeshop.data.shoppingCart.CartSupplierViewModel
import com.example.coffeeshop.ui.theme.CoffeeAppTheme

@Composable
fun CardStorageProduct(
    product: Product,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    shape:RoundedCornerShape = RoundedCornerShape(16.dp),
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    selectedCard:Boolean = false,
    viewModel: CartSupplierViewModel = viewModel()
) {

    val orderList by viewModel.items.collectAsState()
    val isSelected = if (selectedCard) {
        orderList.any { it.id == product.id }
    } else {
        false
    }

    Card(
        modifier = modifier.clickable {
            onClick()
        },
        shape = shape,
        elevation = elevation,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "${formatWithoutZero(product.quantity)} ${product.unitId}",
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.categoryId,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCardStorageProduct(){
    CoffeeAppTheme {
        Surface {
            CardStorageProduct(
                product = productsList[1]
            )
        }
    }
}