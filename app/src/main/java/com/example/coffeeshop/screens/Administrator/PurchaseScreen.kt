package com.example.coffeeshop.screens.Administrator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.product.Product
import com.example.coffeeshop.data.product.Type
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.screens.CardForScreens.CardSupplier
import com.example.coffeeshop.screens.CardForScreens.CatPop
import com.example.coffeeshop.screens.CardForScreens.ChipGroup
import com.example.coffeeshop.screens.CardForScreens.CustomOutlinedTextField
import com.example.coffeeshop.screens.CardForScreens.ThreeStateButton
import com.example.navigationmodule.LocalRouter


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PurchaseScreen() {
    val categories = listOf("Кава", "Чай", "Солодощі", "Холодні напої", "Снеки")
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    val router = LocalRouter.current

    LazyColumn {
        stickyHeader {
            CustomOutlinedTextField()
        }
        item{
            CatPop(
                modifier = Modifier.padding(start = 4.dp),
            ) { onIconStateChange ->
                ChipGroup(
                    categories = categories,
                    selectedCategories = selectedCategories,
                    onCategorySelected = { category ->
                        selectedCategories = category
                    },
                    onIconStateChange = { newState -> onIconStateChange(newState) },
                    modifier = Modifier.padding(start = 24.dp),
                    firstItemThreshold = 330f,
                    endPadding = 350.dp
                )
            }
        }
        items(8){
            CardSupplier(supplierDataList[0]){router.launch(AppRoute.Administrator.Purchase.InformationPurchase)}
        }
    }
}



val supplierDataList = listOf(
    Supplier(
        id = 1,
        nameCompany = "Company",
        email = "Company@gnal.com",
        phoneNumber = "+380345345234",
        products = listOf(
            Product(name ="Еспрfdfgfgdfgdfgdfgdfgесо", category = "Кава",),
            Product(name ="Капучино",category = "Кава"),
            Product(name ="Лате", category = "Кава"),
            Product(name ="Американо", category = "Кава"),
            Product(name ="Торт Чизкейк", category = "Десерт"),
            Product(name ="Круасан", category = "Випічка"),
            Product(name ="Маффін з ягодами", category = "Випічка"),
            Product(name ="Тістечко Шоколадне", category = "Десерт"),
            Product(name ="Фреш з апельсина",category =  "Напої"),
            Product(name ="Чай зелений", category = "Напої")
        ),
        photo = null
    )
)

@Preview(showSystemUi = true)
@Composable
private fun PreviewPurchaseScreen(){
    PurchaseScreen()
}