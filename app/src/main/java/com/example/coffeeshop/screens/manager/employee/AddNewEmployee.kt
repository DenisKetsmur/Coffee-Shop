package com.example.coffeeshop.screens.manager.employee

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.data.filled.sampleEmployee
import com.example.coffeeshop.data.productAndGoods.Goods
import com.example.coffeeshop.data.productAndGoods.GoodsViewModel
import com.example.coffeeshop.data.user.EmployeeViewModel
import com.example.coffeeshop.data.user.Position
import com.example.coffeeshop.data.user.Shift
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.data.user.WorkSchedule
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import com.example.coffeeshop.screens.manager.components.DatePickerDocked
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue


@Composable
fun AddNewEmployeeScreen(viewModel: EmployeeViewModel = viewModel()) {
    AddNewEmployeeContent(
        onClick = { employee ->
            viewModel.add(employee)
        }
    )
}


@Composable
fun AddNewEmployeeContent(
    onClick: (User.Employee)->Unit = {},
) {
    val employee by remember { mutableStateOf(User.Employee()) }

    var name by remember { mutableStateOf(employee.name) }
    var surname by remember { mutableStateOf(employee.surname) }
    var phoneNumber by remember { mutableStateOf(employee.phoneNumber) }
    var email by remember { mutableStateOf(employee.email) }
    var position by remember { mutableStateOf(employee.position) }
    var startJob by remember { mutableStateOf(employee.startJob) }
    var isActive:Boolean by remember { mutableStateOf(employee.isActive) }
    var workSchedule by remember { mutableStateOf(employee.workSchedule) }

    val router = LocalRouter.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                CustomOutlinedInputTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {Text("Ім'я")},
                    modifier = Modifier.fillMaxWidth()
                )
                CustomOutlinedInputTextField(
                    value = surname,
                    onValueChange = {
                        surname = it
                    },
                    label = {Text("Прізвище")},
                    modifier = Modifier.fillMaxWidth()
                )
                CustomOutlinedInputTextField(
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                    },
                    label = {Text("Номер телефону")},
                    modifier = Modifier.fillMaxWidth()
                )
                CustomOutlinedInputTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label =  {Text("Пошта")},
                    modifier = Modifier.fillMaxWidth()
                )
                CustomExposedDropdownMenuBox(
                    firstValue = position.displayName,
                    options = Position.entries.map { it.displayName },
                    onOptionsUpdated = { newValueCategory ->
                        position = Position.entries.first { it.displayName == newValueCategory }
                    },
                    label = {
                        Text("Виберіть категорію")
                    },
                    isSearchable = false
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    DatePickerDocked(
                        onValueChange = { newData ->
                            startJob = newData
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomExposedDropdownMenuBox(
                        firstValue = if (isActive) "Активний" else "Неактивний",
                        options = listOf("Активний", "Неактивний"),
                        onOptionsUpdated = { newValueCategory ->
                            isActive = newValueCategory == "Активний"
                        },
                        label = {
                            Text("Статус")
                        },
                        isSearchable = false,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomExposedDropdownMenuBox(
                        firstValue = workSchedule.shift.time,
                        options = Shift.entries.map { it.time },
                        onOptionsUpdated = { newValueCategory ->
                            workSchedule = workSchedule.copy(
                                shift = Shift.entries.first { it.time == newValueCategory }
                            )
                        },
                        label = {
                            Text("Зміна")
                        },
                        isSearchable = false,
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomOutlinedInputTextField(
                        value = workSchedule.workSchedule,
                        onValueChange = {
                            if (it.length <= 2){
                                workSchedule = workSchedule.copy(
                                    workSchedule = it.filter { it.isDigit() }
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        placeholder = {
                            Text(text = "  / ")
                        },
                        visualTransformation = true,
                        modifier = Modifier.padding(top = 8.dp).width(60.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    CustomOutlinedInputTextField(
                        value = workSchedule.hourlyRate.toString(),
                        onValueChange = {
                            workSchedule = workSchedule.copy(
                                hourlyRate = it.toDouble()
                            )
                        },
                        label =  {Text("Оплата")},
                        singleLine = true,
                        trailingIcon = {
                            Text(text = "грн")
                        },
                        modifier = Modifier.weight(2f)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val newEmployee = employee.copy(
                            name = name,
                            surname = surname,
                            phoneNumber = phoneNumber,
                            email = email,
                            position =position,
                            startJob = startJob,
                            isActive = isActive,
                            workSchedule=workSchedule,
                        )
                        onClick(newEmployee)
                        router.pop()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Зберегти")
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAddNewEmployeeScreen() {
    CoffeeAppTheme(darkTheme = true){
        Surface {
            AddNewEmployeeContent()
        }
    }
}