package com.example.coffeeshop.screens.administrator.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.filled.sampleSupplier
import com.example.coffeeshop.data.supplier.Supplier
import com.example.navigationmodule.LocalRouter

@Composable
fun CardSupplier(
    supplier: Supplier,
    onClick:()->Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = rememberAsyncImagePainter(supplier.imageUri),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .height(128.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.weight(2.5f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = supplier.nameCompany,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Продукти для замовлень: ${supplier.getSupplierRawMaterials()}",
                    style = TextStyle(lineHeight = 23.sp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardSupplier(){
    val router = LocalRouter.current
    LazyColumn {
        items(6){
            CardSupplier(sampleSupplier,
                onClick = {
                    router.launch(AppRoute.Administrator.Purchase.InformationPurchase)
                }
            )
        }
    }
}