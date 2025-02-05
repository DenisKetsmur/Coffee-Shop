package com.example.coffeeshop.screens.Manager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R
import com.example.coffeeshop.data.supplier.Employee
import com.example.coffeeshop.screens.CardForScreens.CardEmployee
import com.example.coffeeshop.screens.CardForScreens.ChipGroup
import com.example.coffeeshop.screens.CardForScreens.CustomOutlinedTextField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmployeeScreen() {
    val categories = listOf("Активний", "Неактивний")
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    LazyColumn {
        stickyHeader {
            CustomOutlinedTextField()
        }
        item {
            ChipGroup(
                categories,
                selectedCategories,
                onCategorySelected = { category ->
                    selectedCategories = category
                },
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        items(6){
            CardEmployee(
                employee = Employee(
                    photo = painterResource(
                        id = R.mipmap.face_photo
                    )
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewEmployeeScreen(){
    EmployeeScreen()
}