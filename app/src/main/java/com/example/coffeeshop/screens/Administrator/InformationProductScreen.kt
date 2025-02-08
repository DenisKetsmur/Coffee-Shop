package com.example.coffeeshop.screens.Administrator

import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@Composable
fun InformationProductScreen(){
    InformationProductContent(
        Product(
            name = "Какао",
            category = "Молоко",
            description = "влаоптвол апвл опж пжовиапжолви пваоп жвлоап вапв" +
                    "в длаптвєдал птвдєлатп євлдатпєдлв тап єваптєвлдатплдєв атп" +
                    "в лдптєдлатпє втапдєлвт аєплдт ваєплвтаєплд тваєдпл твап ",
            price = "24234",
            unit = "мл",
            quantity = 234.0,
        )
    )
}

@Composable
fun InformationProductContent(
    product:Product
) {
    val router = LocalRouter.current
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
                    text = "Категорія: ${product.category}"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Одиниця виміру: ${product.unit!!}"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Кількість: ${product.quantity.toString()}"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Опис: ${product.description!!}"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FloatingActionButton(
                        onClick = {
                            router.launch(AppRoute.Administrator.Storage.EditProduct)
                        },
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        //modifier = Modifier.border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(15.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = null
                        )
                    }
                }

            }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewInformationProductScreen(){
    CoffeeAppTheme(darkTheme = false) {
        InformationProductContent(
            Product(
                name = "Какао",
                category = "Молоко",
                description = "влаоптвол апвл опж пжовиапжолви пваоп жвлоап вапв" +
                        "в длаптвєдал птвдєлатп євлдатпєдлв тап єваптєвлдатплдєв атп" +
                        "в лдптєдлатпє втапдєлвт аєплдт ваєплвтаєплд тваєдпл твап ",
                price = "24234",
                unit = "мл",
                quantity = 234.0,
            )
        )
    }
}