package com.example.coffeeshop.screens.manager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.user.ClientViewModel
import com.example.coffeeshop.data.user.EmployeeViewModel
import com.example.coffeeshop.data.user.User
import com.example.navigationmodule.LocalRouter

@Composable
fun CardClients(
    clientId: Int,
    modifier: Modifier = Modifier,
    viewModel: ClientViewModel = viewModel(),
) {
    val clientList by viewModel.items.collectAsState()
    val client = clientList.find { it.id == clientId }

    if(client == null){
        Text(text = "client null $clientId")
        return
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row {
            Image(
                painter = painterResource(R.mipmap.face_photo),
                contentDescription = null,
                modifier = Modifier.heightIn(min = 128.dp, max = 128.dp),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .weight(2.5f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = "${client.name} ${client.surname}",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = """
                        |Контакти: ${client.phoneNumber}
                        |${client.email}
                    """.trimMargin(),
                    style = TextStyle(lineHeight = 23.sp)
                )
            }
        }
    }
}