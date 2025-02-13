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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.client
import com.example.coffeeshop.data.filled.sampleSupplier
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.data.supplier.Order
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.manager.employee.convertMillisToDate
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter


@Composable
fun InformationClientScreen() {
    InformationClientContent(client = client)
}

@Composable
fun InformationClientContent(client: User.Client) {
    LazyColumn(modifier = Modifier.fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp)) {
        item {
            ClientInfo(client = client)
            Spacer(modifier = Modifier.height(16.dp))
            OrderHistory(client.orders)
        }
    }
}

@Composable
fun ClientInfo(client:User.Client) {
    val router = LocalRouter.current
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier.fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(
                    text = "Інформація",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Ім'я компанії: ${client.name}, ${client.surname}")
                Text(text = "Телефон: ${client.phoneNumber}")
                Text(text = "Пошта: ${client.email}")

            }
            FloatingActionButton(
                onClick = {
                    router.launch(AppRoute.Manager.Clients.EditClient)
                },
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(bottom = 16.dp, end = 16.dp).size(44.dp).align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null
                )
            }
        }

    }
}

@Composable
fun OrderHistory(orders: List<Order<Product>>) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
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

@Composable
fun OrderItem(order: Order<Product>) {
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
            modifier = Modifier.fillMaxWidth(0.4f).align(Alignment.TopStart)
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
            InformationClientContent(
                client
            )
        }
    }
}


