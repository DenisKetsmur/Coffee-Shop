package com.example.coffeeshop.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.R


@Composable
fun MenuScreen() {
    MenuContent()
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MenuContent() {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    Column(
        modifier = Modifier.wrapContentHeight()
    ) {
        LazyColumn {
            stickyHeader {
                TextField(
                    value = searchText,
                    onValueChange = { newText ->
                        searchText = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth()
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
            item {
                val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки")
                var selectedCategory by remember { mutableStateOf<String?>(null) }
                Row {
                    ThreeStateButton()
                    CategoryFilter(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelected = { category ->
                            selectedCategory = category
                        }
                    )
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
}


@Composable
fun ThreeStateButton() {
    var buttonState by remember { mutableStateOf(ButtonState.BYNAME) }

    IconButton(
        onClick = {
            buttonState = when (buttonState) {
                ButtonState.BYNAME -> ButtonState.CHEAPER
                ButtonState.CHEAPER -> ButtonState.RATHER
                ButtonState.RATHER -> ButtonState.BYNAME
            }
        }
    ) {
        Icon(
            painter = when (buttonState) {
                ButtonState.CHEAPER -> painterResource(R.drawable.filterdown)
                ButtonState.RATHER -> painterResource(R.drawable.filterup)
                ButtonState.BYNAME -> painterResource(R.drawable.byname)
            },
            contentDescription = "filter",
            tint = Color.Unspecified
        )
    }
}

enum class ButtonState {
    CHEAPER, RATHER, BYNAME
}


@Composable
fun CategoryFilter(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                label = category,
                isSelected = selectedCategory == category,
                onSelected = { onCategorySelected.invoke(category) }
            )
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = {
            onSelected.invoke()
            focusManager.clearFocus()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.height(40.dp),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(label)
    }
}

@Composable
fun CustomCardProduct(
    nameProduct: String = "name product",
    description: String = "description product",
    price: String = "100",
    image: Int = R.drawable.logo,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Text(
                    text = nameProduct
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = description
                )
            }
            Text(
                text = "$price грн",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 7.dp)
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewMenuScreen() {
    MenuScreen()
}