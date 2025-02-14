package com.example.coffeeshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import com.example.coffeeshop.data.filled.client
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.screens.manager.client.ClientInfo
import com.example.coffeeshop.screens.manager.client.InfoClientContent
import com.example.coffeeshop.screens.manager.client.OrderHistory
import com.example.coffeeshop.screens.manager.components.CardClients
import com.example.coffeeshop.screens.manager.components.CardInfoEmployee
import com.example.coffeeshop.screens.manager.employee.InfoEmployeeContent


@Composable
fun MyProfileScreen() {
    MyProfileContent(
        user = client
    )
}

@Composable
fun MyProfileContent(user: User){
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
        .padding(16.dp)
    ) {
        item{
            when (user) {
                is User.Client -> {
                    ClientInfo(client = user)
                    Spacer(modifier = Modifier.height(16.dp))
                    OrderHistory(client.orders)
                }
                is User.Employee -> {
                    InfoEmployeeContent(employee = user)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Функція в розробці", color = Color.Gray)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMyProfileScreen() {
    CoffeeAppTheme {
        Surface {
            MyProfileScreen()
        }
    }
}