package com.example.coffeeshop.screens.manager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.user.User
import com.example.navigationmodule.LocalRouter

@Composable
fun CardEmployee(
    employee: User.Employee,
){
    val router = LocalRouter.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface
//        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row{
            Image(
                painter = painterResource(employee.photo),
                contentDescription = null,
                modifier = Modifier.weight(1.5f).heightIn(min = 192.dp, max = 192.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.weight(2f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = "${employee.name} ${employee.surname}",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = """
                        |Контакти: ${employee.phoneNumber}
                        |${employee.email}
                        |Посада: ${employee.position}
                        |Зарплата: ${employee.salary}
                        |Стаж: ${employee.experience}
                        |Найм: ${employee.startJob}
                    """.trimMargin(),
                    style = TextStyle(lineHeight = 23.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (employee.status) "активний" else "неактивний",
                    fontWeight = FontWeight.Bold,
                )
            }
            Box(
                contentAlignment = Alignment.BottomEnd
            ){
                FloatingActionButton(
                    onClick = {
                        router.launch(AppRoute.Manager.Personal.EditPersonal)
                    },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}



