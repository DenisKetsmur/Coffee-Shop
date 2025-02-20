package com.example.coffeeshop.screens.shareScreens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.coffeeshop.data.roomDone.ProfileViewModel
import com.example.coffeeshop.data.roomDone.clients.room.entities.Position
import com.example.coffeeshop.screens.manager.client.ClientInfo
import com.example.coffeeshop.screens.manager.client.OrderHistory
import com.example.coffeeshop.screens.manager.components.CardInfoEmployee
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.data.roomDone.clients.entities.Client
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.order.OrderCrudViewModel

@Composable
fun MyProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    userId:Long) {
    @Composable
    fun MyProfileScreen(
        viewModel: ProfileViewModel = viewModel(),
        userId: Long
    ) {
        // Завантажити профіль
        LaunchedEffect(userId) {
            viewModel.loadProfile(userId)
        }

        // Підписуємося на стани
        val client by viewModel.client.collectAsState()
        val employee by viewModel.employee.collectAsState()

        // Відображаємо UI
        when {
            client != null -> {
                val client = client!!.toClient()
                MyProfileContentClient(client = client)
            }
            employee != null -> {
                val employee = employee!!.toEmployee()
                MyProfileContentEmployee(employee = employee)
            }
            else -> {
                Text("Користувача не знайдено")
            }
        }
    }
}

@Composable
fun MyProfileContentEmployee(employee: Employee){
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            CardInfoEmployee(employee = employee)
        }
    }
}


@Composable
fun MyProfileContentClient(
    client: Client,
    viewModel: OrderCrudViewModel = viewModel()
){
    val flow = remember { viewModel.getOrdersWithItemsByClient(client.id) }

    val orderHistory by flow.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
        .padding(16.dp)
    ) {
        item{
            ClientInfo(client = client, isEdit = false )
            Spacer(modifier = Modifier.height(16.dp))
            OrderHistory(orderHistory)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMyProfileScreen() {
    CoffeeAppTheme {
        Surface {
            MyProfileScreen(userId = 1)
        }
    }
}