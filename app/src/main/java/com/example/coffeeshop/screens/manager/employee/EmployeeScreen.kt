package com.example.coffeeshop.screens.manager.employee

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.ScrollViewModel
import com.example.coffeeshop.data.filled.sampleEmployee
import com.example.coffeeshop.data.productAndGoods.GoodsViewModel
import com.example.coffeeshop.data.user.EmployeeViewModel
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.manager.components.CardEmployee
import com.example.coffeeshop.screens.cardForScreens.ChipGroup
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedSearchTextField
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmployeeScreen(viewModel: EmployeeViewModel = viewModel()) {
    val employeeList by viewModel.items.collectAsState()

    val viewModel: ScrollViewModel = viewModel()
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = viewModel.scrollState.value)

    LaunchedEffect(listState.firstVisibleItemIndex) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { viewModel.scrollState.value = it }
    }


    val categories = listOf("Активний", "Неактивний")
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    val router = LocalRouter.current

    LazyColumn(
         state = listState
    ) {
        stickyHeader {
            CustomOutlinedSearchTextField()
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
        items(employeeList){ employee ->
            CardEmployee(
                employeeId = employee.id,
                modifier =  Modifier.clickable {
                    router.launch(AppRoute.Manager.Personal.InfoPersonal(employee.id.toString()))
                },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewEmployeeScreen(){
    CoffeeAppTheme(darkTheme = true) {
        Surface {
            EmployeeScreen()
        }
    }
}