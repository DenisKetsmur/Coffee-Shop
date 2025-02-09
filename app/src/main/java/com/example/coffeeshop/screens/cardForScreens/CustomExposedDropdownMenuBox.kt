package com.example.coffeeshop.screens.cardForScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExposedDropdownMenuBox(
    firstValue:String,
    options:List<String>,
    onOptionsUpdated: (String) -> Unit,
    label: @Composable ()->Unit,
    isSearchable:Boolean = true,
) {
    var searchQuery by remember { mutableStateOf(firstValue) }
    var selectedOption by remember { mutableStateOf(firstValue) }
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var isError by remember { mutableStateOf(false) }

    val displayedOptions = if (isSearchable) {
        options.filter { it.contains(searchQuery, ignoreCase = true) }
    } else {
        options
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = it
        }
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                expanded = true
                onOptionsUpdated(searchQuery)
            },
            singleLine = true,
            readOnly = !isSearchable,
            label = { label() },
            isError = isError && searchQuery != "",
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.hasFocus){
                        expanded = true
                        isError = false
                    }else{
                        isError = !options.contains(searchQuery)
                    }
                }
                .clickable { expanded = true },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

        ) {
            if (displayedOptions.isEmpty()) {
                DropdownMenuItem(
                    text = { Text("Нічого не знайдено", color = Color.Gray) },
                    onClick = { }
                )
            } else {
                displayedOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            searchQuery = option
                            expanded = false
                            focusManager.clearFocus()
                            onOptionsUpdated(option)
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
        firstValue = "dfsdf",
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