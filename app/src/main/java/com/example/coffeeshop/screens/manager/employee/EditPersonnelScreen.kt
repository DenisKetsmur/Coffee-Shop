package com.example.coffeeshop.screens.manager.employee

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.filled.sampleEmployee
import com.example.coffeeshop.data.user.Position
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.cardForScreens.CustomExposedDropdownMenuBox
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditEmployeeScreen() {
    EditEmployeeContent(
        employee = sampleEmployee
    )
}


@Composable
fun EditEmployeeContent(
    employee: User.Employee,
    onClick: ()->Unit = {},
) {
    var name by remember { mutableStateOf(employee.name) }
    var surname by remember { mutableStateOf(employee.surname) }
    var phoneNumber by remember { mutableStateOf(employee.phoneNumber) }
    var email by remember { mutableStateOf(employee.email) }
    var position by remember { mutableStateOf(employee.position) }
    var salary by remember { mutableStateOf(employee.salary) }
    var startJob by remember { mutableStateOf(employee.startJob) }
    var status by remember { mutableStateOf(employee.isActive) }

    val router = LocalRouter.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        //.wrapContentHeight(),
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
                DatePickerDocked(
                    onValueChange = { newData ->
                        startJob = newData
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    CustomOutlinedInputTextField(
                        value = salary.toString(),
                        onValueChange = {
                            salary = it.toDouble()
                        },
                        label =  {Text("Зарплата")},
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomExposedDropdownMenuBox(
                        firstValue = if (status) "Активний" else "Неактивний",
                        options = listOf("Активний", "Неактивний"),
                        onOptionsUpdated = { newValueCategory ->
                            status = newValueCategory == "Активний"
                        },
                        label = {
                            Text("Статус")
                        },
                        isSearchable = false,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        //onSaveSuccess()
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    onValueChange: (Long) -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableLongStateOf(0) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = convertMillisToDate(selectedDate),
            onValueChange = {
                onValueChange(selectedDate)
            },
            label = { Text("Дата початку роботи") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedDate = datePickerState.selectedDateMillis
                            ?.let { it } ?: 0L

//                        selectedDate = datePickerState.selectedDateMillis
//                            ?.let { convertMillisToDate(it) } ?: ""
//

                        showDatePicker = false
                    }) {
                        Text("ОК")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Скасувати")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false
                )
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale("uk"))
    return sdf.format(Date(millis))
}



@Preview(showSystemUi = true)
@Composable
fun PreviewEditEmployeeScreen() {
    CoffeeAppTheme(darkTheme = true){
        Surface {
            EditEmployeeScreen()
        }
    }
}