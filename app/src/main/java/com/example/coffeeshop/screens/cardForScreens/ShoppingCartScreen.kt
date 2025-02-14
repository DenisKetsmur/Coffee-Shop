package com.example.coffeeshop.screens.cardForScreens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.data.filled.rawMaterial
import com.example.coffeeshop.data.product.RawMaterial
import com.example.navigationmodule.LocalRouter
import kotlinx.coroutines.launch


@Composable
fun ShoppingCartScreen(){
    ShoppingCartContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShoppingCartContent() {
    var items by remember { mutableStateOf(rawMaterial.toMutableList()) }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val deletedItemsStack = remember { mutableStateListOf<Pair<Int, RawMaterial>>() }
    val router = LocalRouter.current

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Разом: 0 грн", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Кількість товарів", fontSize = 18.sp)
                }
                Button(
                    onClick = {

                    },
                ) {
                    Text(text = "Замовити")
                }
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            items(items, key = { it.name }) { item ->
                val dismissState = rememberDismissState(
                    confirmStateChange = { dismissValue ->
                        if (dismissValue == DismissValue.DismissedToEnd) {
                            val index = items.indexOf(item)
                            if (index != -1) {
                                // Додаємо видалений елемент у стек
                                deletedItemsStack.add(index to item)

                                // Видаляємо елемент
                                items = items.toMutableList().apply { removeAt(index) }

                                // Показуємо Snackbar
                                scope.launch {
                                    val result = snackBarHostState.showSnackbar(
                                        message = "Видалено ${item.name}",
                                        actionLabel = "Скасувати",
                                        duration = SnackbarDuration.Short
                                    )

                                    if (result == SnackbarResult.ActionPerformed) {
                                        // Повертаємо останній видалений товар
                                        deletedItemsStack.removeLastOrNull()?.let { (restoredIndex, restoredItem) ->
                                            items = items.toMutableList().apply {
                                                add(restoredIndex, restoredItem)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.StartToEnd),
                    background = { DeleteBackground(dismissState) },
                    dismissContent = { ItemCard(item) }
                )
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 90.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(hostState = snackBarHostState)
    }
}

/*@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShoppingCartContent() {
    var items by remember { mutableStateOf(rawMaterial.toMutableList()) }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var tempItem by remember { mutableStateOf<RawMaterial?>(null) }
    var tempIndex by remember { mutableStateOf<Int?>(null) }
    val deletedItemsStack = remember { mutableStateListOf<Pair<Int, RawMaterial>>() }
    val router = LocalRouter.current

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Разом: 0 грн", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Кількість товарів", fontSize = 18.sp)
                }
                Button(
                    onClick = {

                    },
                ) {
                    Text(text = "Замовити")
                }
            }
        },
    ){paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            items(items, key = { it.name }) { item ->
                val dismissState = rememberDismissState(
                    confirmStateChange = { dismissValue ->
                        if (dismissValue == DismissValue.DismissedToEnd) {
                            val index = items.indexOf(item)
                            if (index != -1) {
                                tempItem = item
                                tempIndex = index

                                // Видаляємо елемент
                                items = items.toMutableList().apply { removeAt(index) }

                                // Показуємо Snackbar
                                scope.launch {
                                    snackBarHostState.currentSnackbarData?.dismiss()

                                    val result = snackBarHostState.showSnackbar(
                                        message = "Видалено ${item.name}",
                                        actionLabel = "Скасувати",
                                        duration = SnackbarDuration.Short
                                    )

                                    if (result == SnackbarResult.ActionPerformed) {
                                        // Повертаємо товар на місце
                                        tempItem?.let { restoredItem ->
                                            tempIndex?.let { restoredIndex ->
                                                items = items.toMutableList().apply {
                                                    add(restoredIndex, restoredItem)
                                                }
                                            }
                                        }
                                    }

                                    // Очищаємо temp, щоб уникнути багів
                                    tempItem = null
                                    tempIndex = null
                                }
                            }
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.StartToEnd),
                    background = { DeleteBackground(dismissState) },
                    dismissContent = { ItemCard(item) }
                )

            }

        }

    }

    Box(modifier = Modifier.fillMaxSize()
        .padding(bottom = 90.dp), contentAlignment = Alignment.BottomCenter) {
        SnackbarHost(hostState = snackBarHostState)
    }

}*/


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeleteBackground(dismissState: DismissState) {
    val color by animateColorAsState(
        targetValue = if (dismissState.targetValue == DismissValue.DismissedToEnd)
            MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background,
        label = "Background Color"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier.padding(start = 8.dp)
        ){
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ItemCard(item: RawMaterial) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp, start =  16.dp, end = 16.dp),
            //.background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text(text = item.name, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.price.toString(), fontSize = 18.sp)
            }
            QuantityInput(item)
        }
        //Divider(thickness = 2.dp)
    }
}

@Composable
fun QuantityInput(item: RawMaterial) {
    var quantity by remember { mutableStateOf(0.0) }

    CustomOutlinedInputTextField(
        value = quantity.toString(),
        onValueChange = { quantity = it.toDouble() },
        label = { Text(text = "Кількість") },
        trailingIcon = { Text(text = item.unit, modifier = Modifier.padding(end = 4.dp)) },
        modifier = Modifier.width(140.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewShoppingCartScreen(){
    ShoppingCartScreen()
}