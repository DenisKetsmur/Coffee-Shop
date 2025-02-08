package com.example.coffeeshop.screens.CardForScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.R
import com.example.coffeeshop.data.user.User
import com.example.navigationmodule.LocalRouter
import kotlin.math.max

@Composable
fun CardClients(
    client: User
) {
    val router = LocalRouter.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface
//        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row {
            Image(
                painter = painterResource(R.mipmap.face_photo),
                contentDescription = null,
                modifier = Modifier.weight(1f).heightIn(min = 128.dp, max = 128.dp),
                contentScale = ContentScale.Fit
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