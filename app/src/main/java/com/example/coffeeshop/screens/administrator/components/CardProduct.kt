package com.example.coffeeshop.screens.administrator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R

@Composable
fun CustomCardProduct(
    nameProduct: String = "name product",
    description: String = "description product",
    price: String = "100",
    image: Int = R.drawable.logo,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Text(
                    text = nameProduct
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = description
                )
            }
            Text(
                text = "$price грн",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 7.dp)
            )
        }
    }
}