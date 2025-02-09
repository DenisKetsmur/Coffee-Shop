package com.example.coffeeshop.screens.manager.employee

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.manager.components.CardEmployee
import com.example.coffeeshop.screens.cardForScreens.ChipGroup
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedTextField
import kotlinx.serialization.builtins.UIntArraySerializer

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
                employee = User.Employee()
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewEmployeeScreen(){
    EmployeeScreen()
}