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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.productsList
import com.example.coffeeshop.data.formatting.formatWithoutZero
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@Composable
fun CardStorageProduct(
    product: Product,
    onRoute: () -> Unit,
    modifier: Modifier = Modifier,
    shape:RoundedCornerShape = RoundedCornerShape(16.dp),
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
) {
    Card(
        modifier = modifier.clickable { onRoute.invoke() },
        shape = shape,
        elevation = elevation
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
                    text = "${formatWithoutZero(product.quantity)} ${product.unit}",
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.category,
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
            val router = LocalRouter.current
            CardStorageProduct(
                product = productsList[1],
                onRoute = {router.launch(AppRoute.Administrator.Storage.InformationProduct)}
            )
        }
    }
}