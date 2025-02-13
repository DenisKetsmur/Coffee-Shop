package com.example.coffeeshop.screens.manager.employee

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffeeshop.data.filled.sampleEmployee
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.manager.components.CardInfoEmployee

@Composable
fun InfoEmployeeScreen() {
    InfoEmployeeContent(
        employee = sampleEmployee
    )
}

@Composable
fun InfoEmployeeContent(
    employee: User.Employee
) {
    CardInfoEmployee(
        employee = employee
    )
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
