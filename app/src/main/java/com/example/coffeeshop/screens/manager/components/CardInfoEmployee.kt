package com.example.coffeeshop.screens.manager.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
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
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.coffeeshop.screens.cardForScreens.FilterChip
import com.example.navigationmodule.LocalRouter


@Composable
fun CardInfoEmployee(
    employee: User.Employee
){
    val router = LocalRouter.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row{
            Image(
                painter = painterResource(employee.photo),
                contentDescription = null,
                modifier = Modifier.weight(1.5f).heightIn(min = 192.dp, max = 192.dp),
                contentScale = ContentScale.FillBounds
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
                        |Найм: ${employee.startJob}
                    """.trimMargin(),
                    style = TextStyle(lineHeight = 23.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (employee.isActive) "активний" else "неактивний",
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

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardInfoEmployee(){
    MaterialTheme {
        CardInfoEmployee(
            sampleEmployee
        )
    }
}