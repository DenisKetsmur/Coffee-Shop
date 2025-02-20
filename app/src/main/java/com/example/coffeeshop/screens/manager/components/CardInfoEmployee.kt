package com.example.coffeeshop.screens.manager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.R
import com.example.coffeeshop.data.roomDone.employee.entities.Employee
import com.example.coffeeshop.data.roomDone.workScheduleEntity.WorkScheduleViewModel


@Composable
fun CardInfoEmployee(
    employee: Employee,
    viewModel: WorkScheduleViewModel = viewModel()
) {

    val workSchedule = viewModel.employeeSchedule
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.padding(16.dp).weight(2f)
            ) {
                Text(
                    text = "${employee.lastName} ${employee.firstName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = employee.position.nameView, fontSize = 16.sp)
            }
            Image(
                painter = painterResource(R.mipmap.face_photo),
                contentDescription = "Profile Picture",
                modifier = Modifier.heightIn(min = 192.dp, max = 192.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            RowInfo("Телефон: ", employee.phone)
            RowInfo("Email:", employee.email)
            RowInfo("Дата народження: ", convertMillisToDate(employee.birthDate))
            RowInfo("Дата початку роботи: ", convertMillisToDate(employee.hireDate))
            RowInfo("Активний: ", if (employee.status == 1) "Так" else "Ні")
            Spacer(modifier = Modifier.height(8.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Box(Modifier.fillMaxWidth()) {
                Text(text = "Зміна",
                    modifier = Modifier.align(Alignment.TopStart))
                Text(text = "Графік",
                    modifier = Modifier.align(Alignment.TopCenter))
                Text(text = "Оплата (год)",
                    modifier = Modifier.align(Alignment.TopEnd))
            }
            Divider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(4.dp))
            Box(Modifier.fillMaxWidth()) {
                Text(
                    text = employee.workScheduleId,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Text(
                    text ="${employee.workSchedule.workSchedule[0]}/${employee.workSchedule.workSchedule.drop(1)}",
                    modifier = Modifier.align(Alignment.TopCenter)
                )
                Text(
                    text = employee.workSchedule.paymentPerHour.toString(),
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }

        }
    }
}

@Composable
fun RowInfo(name:String, info:String){
    Box(Modifier.fillMaxWidth()) {
        Text(
            text = name,
            modifier = Modifier.align(Alignment.TopStart),
            fontWeight = FontWeight.Bold
        )
        Text(text = info, Modifier.offset(x = (LocalConfiguration.current.screenWidthDp / 3).dp))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardInfoEmployee(){
    MaterialTheme {
        CardInfoEmployee(
            employee = employees[1]
        )
    }
}