package com.example.coffeeshop.screens.shareScreens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.productAndGoods.Goods
import com.example.coffeeshop.data.productAndGoods.GoodsViewModel
import com.example.coffeeshop.data.productAndGoods.goodsList
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InformationProductScreen(
    goodsId: String,
    viewModel: GoodsViewModel = viewModel()
){
    val goodsList by viewModel.items.collectAsState()
    val goods = goodsList.find { it.id == goodsId.toInt() }

    if (goods == null) {
        Text(text = "товар не знайдено", modifier = Modifier.padding(16.dp))
        return
    }

    InformationProductContent(
        goods = goods
    )
}

@Composable
fun InformationProductContent(
    goods: Goods,
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
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.height(100.dp).weight(2f)
            ) {
                Text(
                    text = "Назва: ${goods.name}"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Категорія: ${goods.category}"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Кількість: ${goods.quantity} ${goods.unit}"
                )
            }
            Image(
                painter = painterResource(goods.image),
                contentDescription = null,
                modifier = Modifier.height(100.dp).aspectRatio(1f),
                contentScale = ContentScale.FillHeight,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Опис: ${goods.description}"
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (!ManagerUser.isClient()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FloatingActionButton(
                        onClick = {
                            router.launch(AppRoute.Menu.EditProduct(goods.id.toString()))
                                  },
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.onPrimary,
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
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewInformationProductScreen(){
    CoffeeAppTheme(darkTheme = false) {
        InformationProductContent(
            goods = goodsList[1]
        )
    }
}