package com.example.coffeeshop.screens.manager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.rawMaterialCategories
import com.example.coffeeshop.data.filled.sampleEmployee
import com.example.coffeeshop.data.filled.unitList
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.data.supplier.Order
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.coffeeshop.screens.cardForScreens.FilterChip
import com.example.navigationmodule.LocalRouter


@Composable
fun CardInfoEmployee(
    employee: User.Employee
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.padding(16.dp).weight(2f)
            ) {
                Text(
                    text = "${employee.surname} ${employee.name}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = employee.position.displayName, fontSize = 16.sp)
            }
            Image(
                painter = painterResource(id = employee.photo),
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
            RowInfo("Телефон: ", employee.phoneNumber)
            RowInfo("Email:", employee.email)
            RowInfo("Вік: ", "${employee.age}")
            RowInfo("Активний: ", if (employee.isActive) "Так" else "Ні")
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
                    text = employee.workSchedule.shift.time,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Text(
                    text ="${employee.workSchedule.workSchedule[0]}/${employee.workSchedule.workSchedule.drop(1)}",
                    modifier = Modifier.align(Alignment.TopCenter)
                )
                Text(
                    text = employee.workSchedule.hourlyRate.toString(),
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
            sampleEmployee
        )
    }
}