package com.example.coffeeshop.screens.manager.employee

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.filled.sampleEmployee
import com.example.coffeeshop.data.user.EmployeeViewModel
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.data.user.employees
import com.example.coffeeshop.screens.manager.components.CardInfoEmployee
import com.example.navigationmodule.LocalRouter

@Composable
fun InfoEmployeeScreen(
    employeeId: String,
    viewModel: EmployeeViewModel = viewModel()
) {
    val employeeList by viewModel.items.collectAsState()
    val employee = employeeList.find { it.id == employeeId.toInt() }

    if (employee == null) {
        Text(text = "Співробітника не знайдено", modifier = Modifier.padding(16.dp))
        return
    }

    InfoEmployeeContent(
        employee = employee
    )
}

@Composable
fun InfoEmployeeContent(
    employee: User.Employee
) {

    val router = LocalRouter.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        CardInfoEmployee(
            employee = employee
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ){
            FloatingActionButton(
                onClick = {
                    router.launch(AppRoute.Manager.Personal.EditPersonal(employee.id.toString()))
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



@Preview(showSystemUi = true)
@Composable
private fun PreviewInfoEmployeeScreen(){
    MaterialTheme{
        Surface {
            InfoEmployeeContent(
                sampleEmployee
            )
        }
    }
}
