package com.example.coffeeshop.screens.cardForScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.R
import com.example.coffeeshop.data.goods.entities.GoodsViewModel
import com.example.coffeeshop.data.goods.entities.goodsList
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CustomCardGoods(
    goodsId: Int,
    viewModel: GoodsViewModel = viewModel(),
    price: String = "100",
    modifier: Modifier = Modifier,
    onRoute: () -> Unit = {},
    enableButtonAddInShoppingCart:Boolean = true,
) {

    val goodsList by viewModel.items.collectAsState()
    val goods = goodsList.find { it.id == goodsId }

    if (goods == null){
        Text("Product not found")
        return
    }
    var buttonEnabled by remember { mutableStateOf(true) }
    Card(
        modifier = modifier
            .clickable { onRoute() }
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp)
        ) {

                Image(
                    painter = painterResource(R.mipmap.face_photo),
                    contentDescription = null,
                    modifier = Modifier.height(150.dp).aspectRatio(1f),
                    contentScale = ContentScale.FillHeight
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(2f)
                ) {
                    Text(
                        text = goods.name,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = goods.category,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${goods.quantity} ${goods.unit}",
                        fontSize = 12.sp
                    )
                }
                Column(
                    modifier = Modifier
                        .height(150.dp)
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$price грн",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    if (enableButtonAddInShoppingCart) {
                        Spacer(modifier = Modifier.height(36.dp))
                        Button(
                            enabled = buttonEnabled,
                            onClick = {
                                buttonEnabled = false
                            }
                        ) {
                            Text(text = "В кошик")
                        }
                    }
                }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewCustomCardProduct(){
    CustomCardGoods(
        goodsId = goodsList[1].id
    )
}