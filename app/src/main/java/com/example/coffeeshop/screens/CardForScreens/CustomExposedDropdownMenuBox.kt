package com.example.coffeeshop.screens.CardForScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffeeshop.data.product.Product
import org.w3c.dom.Text


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExposedDropdownMenuBox(
    options:List<String>,
    onOptionsUpdated: (String) -> Unit,
    label: @Composable ()->Unit,
) {
    var searchQuery by remember { mutableStateOf("") } // Поле пошуку
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) } // Меню відкривається лише при кліку
    var isError by remember { mutableStateOf(false) } // Помилка, якщо нема результатів

    val filteredOptions = options.filter { it.contains(searchQuery, ignoreCase = true) } // Фільтрація

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it } // Контроль відкриття/закриття
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                isError = filteredOptions.isEmpty() // Якщо нема збігів — помилка
                expanded = true // Тримаємо меню відкритим
                onOptionsUpdated(searchQuery)
            },
            label = { label.invoke() },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .clickable { expanded = true }, // Відкриваємо при натисканні

            isError = isError && searchQuery != "", // Підсвічує червоним, якщо є помилка
//            supportingText = {
//                if (isError) Text("Помилка: Нічого не знайдено!", color = MaterialTheme.colorScheme.error)
//            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Закриваємо лише при втраті фокусу
        ) {
            if (filteredOptions.isEmpty()) {
                DropdownMenuItem(
                    text = { Text("Нічого не знайдено", color = Color.Gray) },
                    onClick = { }
                )
            } else {
                filteredOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            searchQuery = option // Записуємо вибір у поле
                            isError = false // Скидаємо помилку
                            expanded = false // Закриваємо меню
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCustomExposedDropdownMenuBox(){
    var category by remember { mutableStateOf("") }
    val categoryList = listOf("Apple", "Banana", "Cherry", "Date", "Fig", "Grapes")
    CustomExposedDropdownMenuBox(
        options = categoryList,
        onOptionsUpdated = { newValueCategory ->
            category = newValueCategory
        },
        label = {
            Text(
                "Виберіть категорію"
            )
        }
    )
}