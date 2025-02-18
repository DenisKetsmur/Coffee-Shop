package com.example.coffeeshop.screens.manager.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.client
import com.example.coffeeshop.data.productAndGoods.Goods
import com.example.coffeeshop.data.supplier.Order
import com.example.coffeeshop.data.user.ClientViewModel
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.data.user.clients
import com.example.coffeeshop.screens.manager.components.convertMillisToDate
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter


@Composable
fun InformationClientScreen(
    clientId: String,
    viewModel: ClientViewModel = viewModel()
) {
    val router = LocalRouter.current

    val clientList by viewModel.items.collectAsState()
    val client = clientList.find { it.id == clientId.toInt() }

    if (client == null) {
        Text(text = "клієнта не знайдено", modifier = Modifier.padding(16.dp))
        return
    }

    InfoClientContent(
        client = client,
        onClick = {
            router.launch(AppRoute.Manager.Clients.EditClient(clientId))
        }
    )
}

@Composable
fun InfoClientContent(
    client: User.Client,
    onClick:()-> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        item {
            ClientInfo(
                client = client,
                onClick = onClick,
            )
            Spacer(modifier = Modifier.height(16.dp))
            OrderHistory(client.orders)
        }
    }
}

@Composable
fun ClientInfo(
    client:User.Client,
    modifier: Modifier = Modifier,
    isEdit:Boolean = true,
    onClick: ()-> Unit = {},
) {
    val router = LocalRouter.current
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier.fillMaxWidth()

    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(
                    text = "Інформація",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Ім'я клієнта: ${client.name} ${client.surname}")
                Text(text = "Телефон: ${client.phoneNumber}")
                Text(text = "Пошта: ${client.email}")

            if(isEdit){
                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 16.dp),
                    contentAlignment = Alignment.BottomEnd
                ){
                    FloatingActionButton(
                        onClick = {
                            onClick()
                        },
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(44.dp)
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

@Composable
fun OrderHistory(orders: List<Order<Goods>>) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
    ) {
        if (orders.isEmpty()) {
            Text(
                text = "Клієнт ще нічого не придбав",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }else{
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Історія замовлень",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                orders.forEach { order ->
                    OrderItem(order)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun OrderItem(order: Order<Goods>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Дата: ${convertMillisToDate(order.date)}", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Назва")
            Text(text = "Кількість")
            Text(text = "Ціна")
        }
        Divider(thickness = 2.dp)
        order.items.forEach { item ->
            OrderRow(item.name, item.quantity.toString(), item.price.toString())
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Сума: ${order.total}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun OrderRow(name: String, quantity: String, price: String) {
    Box(Modifier.fillMaxWidth()) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .align(Alignment.TopStart)
        )
        Text(text = quantity, modifier = Modifier.align(Alignment.TopCenter))
        Text(text = price, modifier = Modifier.align(Alignment.TopEnd))
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewInformationClientScreen(){
    CoffeeAppTheme(darkTheme = true) {
        Surface {
            InfoClientContent(
                client = clients[1],
            )
        }
    }
}


