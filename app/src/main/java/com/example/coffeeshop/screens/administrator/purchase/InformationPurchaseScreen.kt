package com.example.coffeeshop.screens.administrator.purchase

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.filled.sampleSupplier
import com.example.coffeeshop.data.formatting.formatWithoutZero
import com.example.coffeeshop.data.productAndGoods.Product
import com.example.coffeeshop.data.supplier.Order
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.data.supplier.SupplierViewModel
import com.example.coffeeshop.data.supplier.suppliers
import com.example.coffeeshop.data.user.ManagerSupplier
import com.example.coffeeshop.screens.manager.components.convertMillisToDate
import com.example.coffeeshop.ui.theme.CoffeeAppTheme
import com.example.navigationmodule.LocalRouter


@Composable
fun InformationPurchaseScreen(
    supplierId: String,
    viewModel: SupplierViewModel = viewModel()
) {
    val supplierList = viewModel.items.collectAsState()
    val supplier = supplierList.value.find { it.id == supplierId.toInt() }

    if(supplier == null){
        Text(text = "supplier null")
        return
    }

    InformationPurchaseContent(
        supplier = supplier,
    )
}

@Composable
fun InformationPurchaseContent(supplier: Supplier) {
    val router = LocalRouter.current
    LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        item{
            SupplierInfo(supplier){
                router.launch(AppRoute.Administrator.Purchase.EditSupplier(supplier.id.toString()))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OrderHistory(supplier.orders)
        }
    }
}

@Composable
fun SupplierInfo(
    supplier: Supplier,
    onClick: ()-> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier.fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Інформація",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ім'я компанії: ${supplier.nameCompany}")
            Text(text = "Телефон: ${supplier.phoneNumber}")
            Text(text = "Пошта: ${supplier.email}")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ){
            FloatingActionButton(
                onClick = { onClick() },
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 16.dp)
                    .size(44.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = stringResource(R.string.edit)
                )
            }
        }
    }
}

@Composable
fun OrderHistory(orders: List<Order<Product>>) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Історія замовлень",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            orders.forEach { order ->
                OrderItem(order)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun OrderItem(order: Order<Product>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Дата: ${convertMillisToDate(order.date)}", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Назва")
            Text(text = "Кількість")
            Text(text = "Ціна (грн)")
        }
        Divider(thickness = 2.dp)
        order.items.forEach { item ->
            OrderRow(item.name, item.quantity, item.price)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Сума: ${formatWithoutZero(order.total)} грн",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun OrderRow(name: String, quantity: Float, price: Double) {
    Box(Modifier.fillMaxWidth()) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .align(Alignment.TopStart)
        )
        Text(text = formatWithoutZero(quantity), modifier = Modifier.align(Alignment.TopCenter))
        Text(text = formatWithoutZero(price), modifier = Modifier.align(Alignment.TopEnd))
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewInformationPurchaseScreen(){
    CoffeeAppTheme {
        Surface {
            InformationPurchaseScreen(
                supplierId = suppliers[1].id.toString()
            )
        }
    }
}


