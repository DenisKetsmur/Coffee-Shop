package com.example.coffeeshop.screens.manager.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    onValueChange: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableLongStateOf(0) }

    Box(modifier = modifier) {
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