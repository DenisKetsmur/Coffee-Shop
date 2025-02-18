package com.example.coffeeshop.screens.manager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.sampleEmployee
import com.example.coffeeshop.data.productAndGoods.GoodsViewModel
import com.example.coffeeshop.data.user.EmployeeViewModel
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.data.user.employees
import com.example.navigationmodule.LocalRouter

@Composable
fun CardEmployee(
    employeeId: Int,
    viewModel: EmployeeViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    val employeeList by viewModel.items.collectAsState()
    val employee = employeeList.find { it.id == employeeId }

    if (employee == null){
        Text("Product not found")
        return
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(employee.photo),
                contentDescription = null,
                modifier = Modifier.heightIn(min = 150.dp, max = 142.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
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
                    """.trimMargin(),
                    style = TextStyle(lineHeight = 23.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (employee.isActive) "активний" else "неактивний",
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCardEmployee(){
    MaterialTheme {
        CardEmployee(
            sampleEmployee.id
        )
    }
}



