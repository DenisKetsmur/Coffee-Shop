package com.example.coffeeshop.screens.Manager

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.supplier.Employee
import com.example.navigationmodule.LocalRouter

@Composable
fun EditPersonnelScreen() {
    //CardPersonnel()
}

@Composable
fun CardPersonnel(
    personnel: Employee,
    onClick: ()->Unit = {},
) {
    val router = LocalRouter.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), shape = RoundedCornerShape(10)
    ) {
        Row {
            Image(
                painter = personnel.photo,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = "${personnel.name} ${personnel.surname}",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = """
                        |Контакти: ${personnel.phoneNumber}
                        |${personnel.email}
                        |Посада: ${personnel.position}
                        |Зарплата: ${personnel.salary}
                        |Стаж: ${personnel.experience}
                        |Найм: ${personnel.startJob}
                    """.trimMargin(), style = TextStyle(lineHeight = 23.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = personnel.status,
                    fontWeight = FontWeight.Bold,
                )
            }
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButton(
                    onClick = {
                        //router.launch(AppRoute.Manager.Personal.EditPersonal)
                    }, modifier = Modifier.padding(10.dp)
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
