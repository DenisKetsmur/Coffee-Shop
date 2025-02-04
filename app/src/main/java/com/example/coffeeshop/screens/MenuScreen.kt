package com.example.coffeeshop.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R
import com.example.coffeeshop.screens.CardForScreens.CatPop
import com.example.coffeeshop.screens.CardForScreens.ChipGroup
import com.example.coffeeshop.screens.CardForScreens.CustomCardProduct
import com.example.coffeeshop.screens.CardForScreens.ThreeStateButton


@Composable
fun MenuScreen() {
    MenuContent()
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuContent() {
    var searchText by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки")
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    var iconState: Int by remember { mutableStateOf(R.drawable.cat_close_mouth) }

    LazyColumn(
        modifier = Modifier.fillMaxSize()

    ) {
        stickyHeader {
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .focusRequester(focusRequester),
                shape = RoundedCornerShape(28),
                label = {
                    Text(
                        text = "Search",
                        color = Color.Gray
                    )
                },
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            searchText = ""
                            focusManager.clearFocus()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Description",
                            tint = Color.Unspecified
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }
        item{
            Row(
                modifier = Modifier.fillMaxWidth(),
            ){
                ThreeStateButton(
                    modifier = Modifier.padding(start = 16.dp, top = 36.dp)
                )
                CatPop(
                    iconState = iconState,
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    ChipGroup(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelected = { category ->
                            selectedCategory = category
                        },
                        onIconStateChange = { newState -> iconState = newState },
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }
            }
        }
        item {
            CustomCardProduct()
            CustomCardProduct()
            CustomCardProduct()
            CustomCardProduct()
            CustomCardProduct()
            CustomCardProduct()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewMenuScreen() {
    MenuScreen()
}