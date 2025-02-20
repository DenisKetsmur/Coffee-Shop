package com.example.coffeeshop.screens.shareScreens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.data.user.ManagerUser.users
import com.example.coffeeshop.screens.manager.client.ClientInfo
import com.example.coffeeshop.screens.manager.client.OrderHistory
import com.example.coffeeshop.screens.manager.components.CardInfoEmployee


@Composable
fun MyProfileScreen(userId:String) {
    MyProfileContent(user = users[userId.toInt()-1])
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
                    ClientInfo(client = user, isEdit = false )
                    Spacer(modifier = Modifier.height(16.dp))
                    OrderHistory(user.orders)
                }
                is User.Employee -> {
                    CardInfoEmployee(employee = user, )
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
            MyProfileScreen(userId = "1")
        }
    }
}