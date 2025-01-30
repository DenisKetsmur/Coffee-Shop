package com.example.coffeeshop.screens.Administrator

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.R
import com.example.coffeeshop.screens.componentsMenuScreen.ChipGroup
import com.example.navigationmodule.LocalRouter


@Composable
fun PurchaseScreen() {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки")
    var selectedCategory by remember { mutableStateOf<String?>(null) }


    LazyColumn {
        item {
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
            ChipGroup(
                categories,
                selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )
        }
        item{
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
            CardSupplier(supplier = Supplier(photo = painterResource(id = R.mipmap.face_photo)))
        }
    }
}


@Composable
fun CardSupplier(
    supplier: Supplier,
){
    val router = LocalRouter.current
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            .clickable {
                //
            },
        shape = RoundedCornerShape(10)
    ) {
        Row{
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape) // Додаємо круглу рамку
            ){
                Image(
                    painter = supplier.photo,
                    modifier = Modifier.size(100.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier.weight(2f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = supplier.nameCompany,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Категорії для замовлень: ${supplier.category.keys.joinToString(", ")}}",
                            style = TextStyle(lineHeight = 23.sp)
                )
            }
        }
    }
}


data class Supplier(
    val id:Int = 1,
    val nameCompany: String = "Bober",
    val email:String = "kurva@gnal.com",
    val phoneNumber: String = "234234234234",
    val category:Map<String, Double> = mapOf(
        "ghjler" to 124.0,
        "Кава" to 234.0,
        "Чай" to 234.0,
        "Солодощі" to 234.0,
        "Холодні напої" to 123.0,
        "Снеки" to 123.0,
    ),
    val photo: Painter
)


@Preview(showSystemUi = true)
@Composable
private fun PreviewPurchaseScreen(){
    PurchaseScreen()
}